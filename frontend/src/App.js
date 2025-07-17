import logo from "./logo.svg";
import "./App.css";
import NewsListScreen from "./components/NewsListScreen";
import { useState } from "react";

function App() {
  const [showNewsList, setshowNewsList] = useState(false);

  function showNews() {
    setshowNewsList(true);
  }

  if (!showNewsList) {
    return (
      <div className="App">
        <button onClick={showNews}>Get News</button>
        {showNewsList && NewsListScreen}
      </div>
    );
  }
  return <NewsListScreen onBack={() => setshowNewsList(false)} />;
}

export default App;
