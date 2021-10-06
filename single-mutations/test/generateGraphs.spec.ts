
import { expect } from 'chai';
import {generateGraphs} from '../src/predicates'

it('generateGraphs(predicate = "bt", size = 1) => 1', async () => {
    let graphs = await generateGraphs("bt", 1)
    expect(1).to.equal(graphs.length)
})

it('generateGraphs(predicate = "bt", size = 2) => 4', async () => {
    let graphs = await generateGraphs("bt", 2)
    expect(4).to.equal(graphs.length)
})

it('generateGraphs(predicate = "sll", size = 1) => 1', async () => {
    let graphs = await generateGraphs("sll", 1)
    expect(1).to.equal(graphs.length)
})

it('generateGraphs(predicate = "sll", size = 2) => 2', async () => {
    let graphs = await generateGraphs("sll", 2)
    expect(2).to.equal(graphs.length)
})

it('generateGraphs(predicate = "csll", size = 1) => 1', async () => {
    let graphs = await generateGraphs("csll", 1)
    expect(1).to.equal(graphs.length)
})

it('generateGraphs(predicate = "csll", size = 2) => 2', async () => {
    let graphs = await generateGraphs("csll", 2)
    expect(2).to.equal(graphs.length)
})

it('generateGraphs(predicate = "dll", size = 1) => 1', async () => {
    let graphs = await generateGraphs("dll", 1)
    expect(1).to.equal(graphs.length)
})

it('generateGraphs(predicate = "dll", size = 2) => 2', async () => {
    let graphs = await generateGraphs("dll", 2)
    expect(2).to.equal(graphs.length)
})