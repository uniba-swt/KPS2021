
% This file contains Prolog functions that are not provided by Tau Prolog out of the box

% taken from http://tau-prolog.org/documentation/prolog/lists/member/2
member(X,[X|_]).
member(X,[_|Xs]) :- member(X,Xs).

% taken from http://tau-prolog.org/documentation/prolog/lists/append/3
append([],X,X).
append([H|T],X,[H|S]) :- append(T,X,S).

% implementation of https://www.swi-prolog.org/pldoc/man?predicate=delete/3
% delete(+List1, @Elem, -List2)
delete([], _, []).
delete([L1|L1s], L1, L1s).
delete([L1|L1s], E, L2) :- L1 \= E, delete(L1s, E, L2x), append([L1], L2x, L2).
