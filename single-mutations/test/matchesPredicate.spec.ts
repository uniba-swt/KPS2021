
import { expect } from 'chai';
import config from '../src/config';
import {generateGraphs, matchesPredicate} from '../src/predicates'
import { CellGraph } from '../src/types';

it('matchesPredicate corrupt sll due to cycle', async () => {
    let predicate = "sll"
    let graph: CellGraph = {
        entries: ['n1'],
        matrix: {
            'n1': { next: 'n1' },
        },
        consumed: []
    }

    expect(false).to.equal(await matchesPredicate(graph, predicate))    
})

it('matchesPredicate corrupt sll due to null', async () => {
    let predicate = "sll"
    let graph: CellGraph = {
        entries: ['null'],
        matrix: {
            'n1': { next: 'null' },
        },
        consumed: []
    }

    expect(false).to.equal(await matchesPredicate(graph, predicate))    
})


it('matchesPredicate sll 1', async () => {
    let predicate = "sll"
    let size = 1
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})

it('matchesPredicate sll 2', async () => {
    let predicate = "sll"
    let size = 2
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})

it('matchesPredicate bt 1', async () => {
    let predicate = "bt"
    let size = 1
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})

it('matchesPredicate bt 2', async () => {
    let predicate = "bt"
    let size = 2
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})

it('matchesPredicate csll 1', async () => {
    let predicate = "csll"
    let size = 1
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})

it('matchesPredicate csll 2', async () => {
    let predicate = "csll"
    let size = 2
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})
it('matchesPredicate dll 1', async () => {
    let predicate = "dll"
    let size = 1
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})

it('matchesPredicate dll 2', async () => {
    let predicate = "dll"
    let size = 2
    let graphs = await generateGraphs(predicate, size)
    graphs.forEach(async g => {
        expect(true).to.equal(await matchesPredicate(g, predicate))    
    })
})