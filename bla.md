I’d turn it into a small “blog grew too quickly” story, then repair it in focused commits.

   Commit                                     Realistic change                           Codeps signal                               Refactor lesson
  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
   feat: add author lookup to post            Make the current cycle real: posts need    SCC blog.posts, blog.users; inspect         Don’t accept a cut suggestion blindly—
   browsing                                   author data; users expose posts            witness and extFanIn                        find the ownership mistake.
  ─────────────────────────────────────────  ─────────────────────────────────────────  ──────────────────────────────────────────  ──────────────────────────────────────────
   refactor: separate blog vocabulary from    Add blog.model with UserId, PostId, and    Cycle disappears; nodesInCycles,            Shared vocabulary belongs in a third,
   features                                   Post(authorId); remove User.posts:         internalEdges fall                          lower-level package; dependencies point
                                              List[Post]                                                                             toward it.
  ─────────────────────────────────────────  ─────────────────────────────────────────  ──────────────────────────────────────────  ──────────────────────────────────────────
   feat: add a quick admin dashboard          Put querying, formatting, moderation,      High file/package propagator: high fanIn    A hub is a change amplifier, not
                                              and storage access in one                  + fanOut, score > 1                         automatically a reusable abstraction.
                                              AdminDashboard/BlogServices façade
  ─────────────────────────────────────────  ─────────────────────────────────────────  ──────────────────────────────────────────  ──────────────────────────────────────────
   refactor: split dashboard into use         Introduce narrow UserDirectory,            Propagator score falls; file-level          Prefer narrow ports and explicit
   cases                                      PostCatalog, and a Moderation use case;    report pinpoints improvement                composition over a “services” bucket.
                                              wire them in main
  ─────────────────────────────────────────  ─────────────────────────────────────────  ──────────────────────────────────────────  ──────────────────────────────────────────
   feat: support bulk user import             Expose var users, a mutable cache, or      mutPorts, publicMutableSurface, and         Mutable public state is an untracked
                                              public mutable collection as the           exposure rise                               coupling channel.
                                              expedient implementation
  ─────────────────────────────────────────  ─────────────────────────────────────────  ──────────────────────────────────────────  ──────────────────────────────────────────
   refactor: encapsulate user storage         Keep collection private; expose            mutPorts: 0, lower exposure                 Expose operations, not state.
                                              behavior such as register, find, all;
                                              return immutable snapshots
  ─────────────────────────────────────────  ─────────────────────────────────────────  ──────────────────────────────────────────  ──────────────────────────────────────────
   feat: prepare notifications                Add a notifications package that is        Orphan                                      Orphans are investigation candidates,
                                              never referenced                                                                       not automatic deletions.
  ─────────────────────────────────────────  ─────────────────────────────────────────  ──────────────────────────────────────────  ──────────────────────────────────────────
   refactor: remove abandoned notification    Delete it after confirming it is not an    Orphan disappears                           A deliberately boring cleanup
   spike                                      entry point/reflection target                                                          demonstrates safe metric-driven
                                                                                                                                     deletion.

  The strongest version uses the current bad commits as the “before” state, followed immediately by repair commits. Avoid an artificial unused import merely to create a graph
  edge; make every bad dependency correspond to a plausible product request.

  For each commit, run the same loop:

  deder exec -t compile -m cli
  java -jar codeps.jar status

  Then inspect only the affected area:

  # Package knot / architecture question
  java -jar codeps.jar report-packages --include blog --analyze-cuts
  java -jar codeps.jar inspect-cycle --id scc:blog.posts

  # After a package is selected, identify the responsible source files
  java -jar codeps.jar report-files --include blog.posts
  java -jar codeps.jar inspect-node --scope files --id blog/src/posts/PostsApi.scala

  I’d make the README a guided lab with a short “what changed / why it matters” section for each tagged commit. The key is to teach metric interpretation rather than score-
  chasing:

  - cycles, nodesInCycles, internalEdges: resolve first; use extFanIn to prioritize the cycle with the wider blast radius.
  - propagators: investigate high fanIn and fanOut; split responsibilities or narrow the API, rather than reflexively splitting files.
  - ports, mutPorts, exposure: inspect the breakdown; remove public mutability first, then reduce genuinely unused surface.
  - dependentsPerPublicPort / “use”: a low value is a question—“is this API too broad?”—not proof that it is bad.
  - orphans: confirm no runtime entry point or reflective use before removal.
  - Overall health: treat it as a trend/dashboard, not the optimization target. A small feature can legitimately add edges; the important outcome is clearer, one-way
    ownership.

  Commit .codeps/config.yaml and .codeps/root.ndjson; leave .codeps/out/ as generated inspection material. This gives the repo an appealing arc: feature pressure creates
  understandable architectural debt, Codeps locates it, and a modest refactor makes the dependency direction and public surface healthier.

