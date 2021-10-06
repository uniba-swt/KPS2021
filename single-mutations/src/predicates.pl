
% This file contains shape predicate functions

% null-terminated singly-linked list
sll(null, N, N, _).
sll(T, N, Nx, Edges) :- member(T, N),
                        delete(N, T, N1),
                        member([next, T, Next], Edges),
                        sll(Next, N1, Nx, Edges).

% null-terminated binary tree
bt(null, N, N, _).
bt(T, N, Nx, F) :-  member(T, N),
                    delete(N, T, N1),
                    % member(L, [null|N1]),
                    member([left, T, L], F),
                    bt(L, N1, N2, F),
                    % member(R, [null|N2]),
                    member([right, T, R], F),
                    bt(R, N2, Nx, F).

% null-terminated cyclic singly-linked list 
csll(null, N, N, _).
csll(T, N, Nx, F) :-     member(T, N),
                        delete(N, T, N1),
                        member([next, T, Next], F),
                        csll(Next, T, N1, Nx, F).
csll(T, T, N, N, _).
csll(T, Start, N, Nx, F) :- member(T, N),
                            delete(N, T, N1),
                            member([next, T, Next], F),
                            csll(Next, Start, N1, Nx, F).

% null-terminated doubly-linked list
dll(null, N, N, _).
dll(T, N, Nx, F) :- member(T, N),
                    delete(N, T, N1),
                    member([fwd, T, Fwd], F),
                    member([bwd, T, null], F),
                    dll(Fwd, T, N1, Nx, F).
dll(null, _, [], [], _).
dll(T, Bwd, N, Nx, F) :-    member(T, N),
                            delete(N, T, N1),
                            member([fwd, T, Fwd], F),
                            member([bwd, T, Bwd], F),
                            dll(Fwd, T, N1, Nx, F).

% null-terminated cyclic doubly-linked list
cdll(null, N, N, _).
cdll(T, N, N1, F) :-    member(T, N),
                        delete(N, T, N1),
                        member([next, T, T], F),
                        member([previous, T, T], F).
cdll(T, N, Nx, F) :-    member(T, N),
                        delete(N, T, N1),
                        member([next, T, Fwd], F),
                        member([previous, T, Bwd], F),
                        cdll(Fwd, Bwd, T, T, N1, Nx, F).
cdll(T, T, Fwd, Bwd, N, N1, F) :-  member(T, N),
                                        delete(N, T, N1),
                                        member([previous, T, Bwd], F),
                                        member([next, T, Fwd], F).
cdll(T, Last, Start, Bwd, N, Nx, F) :-  member(T, N),
                                        delete(N, T, N1),
                                        member([previous, T, Bwd], F),
                                        member([next, T, Fwd], F),
                                        cdll(Fwd, Last, Start, T, N1, Nx, F).
