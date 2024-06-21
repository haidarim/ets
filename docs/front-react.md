## Routing: used in Single Page Application (SPA)
Think yiu have a single page as application and want to change the page's content in clinet in only clinet side based on client's choice. 

We can have a BrowserRouter from `react-router-dom` to define different `Routes` which also belongs to the same node module.

In the `Routes` we define each `Route` which is a component/page in its own path. 
In this way we can define route that have its own path (url) and loads its own component when the link belonging to the path being trigged. 

ex:
```js 
<Router>
    <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/right-route" element={<Comp1/>} />
        <Route path="/left-route" element={<Comp2/>} />
    </Routes>
</Router>
```
To visit each route we can use `Link`, this tags points to each rout and can change the page,the path of the route take place in the `to` attribute of the Link tag. 
ex: 
```js
const Home = ()=>{
    return(
        <div>
            <p>hello</p><br/>
            <Link to="/right-route">click to go to the right route</Link>
        </div>
    );
}
```

**Routing all bad urls:** to route all bad urls to an `404 page not founded` we can use a route having `path="*"` and defining the component we want to show. 


