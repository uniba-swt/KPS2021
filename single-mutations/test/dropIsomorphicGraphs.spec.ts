
import { expect } from 'chai';
import {dropIsomorphicGraphs, generateGraphs} from '../src/predicates'

it('dropIsomorphicGraphs(predicate = "bt", size = 2) => 4', async () => {
    let graphs = dropIsomorphicGraphs(await generateGraphs("bt", 2))
    expect(2).to.equal(graphs.length)
})

it('dropIsomorphicGraphs(predicate = "sll", size = 1) => 1', async () => {
    let graphs = dropIsomorphicGraphs(await generateGraphs("sll", 1))
    expect(1).to.equal(graphs.length)
})

it('dropIsomorphicGraphs(predicate = "sll", size = 2) => 2', async () => {
    let graphs = dropIsomorphicGraphs(await generateGraphs("sll", 2))
    expect(1).to.equal(graphs.length)
})

it('dropIsomorphicGraphs(predicate = "csll", size = 1) => 1', async () => {
    let graphs = dropIsomorphicGraphs(await generateGraphs("csll", 1))
    expect(1).to.equal(graphs.length)
})

it('dropIsomorphicGraphs(predicate = "csll", size = 2) => 2', async () => {
    let graphs = dropIsomorphicGraphs(await generateGraphs("csll", 2))
    expect(1).to.equal(graphs.length)
})

it('dropIsomorphicGraphs(predicate = "dll", size = 1) => 1', async () => {
    let graphs = dropIsomorphicGraphs(await generateGraphs("dll", 1))
    expect(1).to.equal(graphs.length)
})

it('dropIsomorphicGraphs(predicate = "dll", size = 2) => 2', async () => {
    let graphs = dropIsomorphicGraphs(await generateGraphs("dll", 2))
    expect(1).to.equal(graphs.length)
})