## Routing: used in Single Page Application (SPA)

Think yiu have a single page as application and want to change the page's content in clinet in only clinet side based on client's choice.

We can have a BrowserRouter from `react-router-dom` to define different `Routes` which also belongs to the same node module.

In the `Routes` we define each `Route` which is a component/page in its own path.
In this way we can define route that have its own path (url) and loads its own component when the link belonging to the path being trigged.

ex:

```js
<Router>
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/right-route" element={<Comp1 />} />
    <Route path="/left-route" element={<Comp2 />} />
  </Routes>
</Router>
```

To visit each route we can use `Link`, this tags points to each rout and can change the page,the path of the route take place in the `to` attribute of the Link tag.
ex:

```js
const Home = () => {
  return (
    <div>
      <p>hello</p>
      <br />
      <Link to="/right-route">click to go to the right route</Link>
    </div>
  );
};
```

**Routing all bad urls:** to route all bad urls to an `404 page not founded` we can use a route having `path="*"` and defining the component we want to show.

## Creating functions in different ways: Declaration ,expression, arrow.

1. Function Declaration:
   syntax:

```js
function myFun() {
  //....
}
```

function declarations are hoisted to the top of their scope, i.e. we can call the function before its declaration in the code.

2. Function Expression:
   syntax:

```js
const myFun = function () {
  // ...
};
```

Not hoisted, i.e. function expressions are not reachable before definition.

3. Arrow Functions:
   syntax:

```js
const myFun = () => {
  // ...
};
```

Not hoisted, i.e. function expressions are not reachable before definition.

Note: all type of definitions supports `this Binding` dynamically. But only the function declaration can be used as constructor and supports `new` keyword.

## React Hooks:

Hooks are special functions that lets the developer to "hook into" React features from function components. Hooks provides lifecycle methods and other features to simplify somponents logic and enable a more functional programming style in React.

### Core Hooks:

1. `useState`: Manages states in a functional component. Allows to declare state variables and their updater functions. Returns an array with the current state and a function to update it. The initial value is passed as argument to the useState.
   ex:

```js
import React, { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0); // initial value

  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}> Increment</button>
    </div>
  );
}
```

2. `useRef`: Access and manipulate DOM elements or persist values across renders without causing re-renders. Creates a reference to a DOM element or store a mutable value. Returns a mutable object with a `.current` property.

ex:

```js
import React, { useRef, useEffect } from "react";

function FocusInput() {
  const inputRef = useRef(null);

  useEffect(() => {
    inputRef.current.focus(); // Focuses the input element on mount
  }, []);

  return <input ref={inputRef} />;
}
```

3. `useEffect`: Performs side effects in function components, such as data fetching, subscriptions, or manually changing the DOM. Runs side effects after rendering or in response to changes in dependencies.

**Empty vs Non-empty dependency array** If the dependency array is empty, the `useEffect` hook will run only once, after the initial render of theh component, This is similar to `componentDidMount` in class component. The effect will not run again unless the component is umounted and remounted.

If the dependency array is not empty, i.e. contains one or more values, the `useEffect` hook will run after initial render and also whenever any of the specified dependencies changes. Each time any dependency in the array changes, the effect will be re-run.

ex:

```js
import React, { useState, useEffect } from "react";

function FetchData() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch("https://api.example.com/data")
      .then((response) => response.json())
      .then((data) => setData(data));
  }, []); // Runs once after the initial render

  return <div>{data ? data.title : "Loading..."}</div>;
}
```
