import { InMemoryDbService } from 'angular-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const restaurants = [
      { id: 0,  name: 'Zero', items:[{name:'1', price:12.99, restaurantName:'Zero'}, {name:'2', price:8.99, restaurantName:'Zero'}] },
      { id: 11, name: 'Mr. Nice', items:[{name:'1', price:12.99, restaurantName:'Mr. Nice'}, {name:'2', price:8.99, restaurantName:'Mr. Nice'}]},
      { id: 12, name: 'Narco', items:[{name:'1', price:12.99, restaurantName:'Narco'}, {name:'2', price:8.99, restaurantName:'Narco'}] },
      { id: 13, name: 'Bombasto', items:[{name:'1', price:12.99, restaurantName:'Bombasto'}, {name:'2', price:8.99, restaurantName:'Bombasto'}]},
      { id: 14, name: 'Celeritas', items:[{name:'1', price:12.99, restaurantName:'Celeritas'}, {name:'2', price:8.99, restaurantName:'Celeritas'}] },
      { id: 15, name: 'Magneta', items:[{name:'1', price:12.99, restaurantName:'Magneta'}, {name:'2', price:8.99, restaurantName:'Magneta'}] },
      { id: 16, name: 'RubberMan', items:[{name:'1', price:12.99, restaurantName:'RubberMan'}, {name:'2', price:8.99, restaurantName:'RubberMan'}] },
      { id: 17, name: 'Dynama', items:[{name:'1', price:12.99, restaurantName:'Dynama'}, {name:'2', price:8.99, restaurantName:'Dynama'}] },
      { id: 18, name: 'Dr IQ', items:[{name:'1', price:12.99, restaurantName:'Dr IQ'}, {name:'2', price:8.99, restaurantName:'Dr IQ'}] },
      { id: 19, name: 'Magma', items:[{name:'1', price:12.99, restaurantName:'Magma'}, {name:'2', price:8.99, restaurantName:'Magma'}] },
      { id: 20, name: 'Tornado', items:[{name:'1', price:12.99, restaurantName:'Tornado'}, {name:'2', price:8.99, restaurantName:'Tornado'}] }
    ];
    return {restaurants};
  }
}
