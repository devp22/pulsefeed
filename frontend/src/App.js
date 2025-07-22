import React, { useState } from "react";
import NewsListScreen from "./components/NewsListScreen";
import { DropdownButton, Dropdown } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

function App() {
  const [showNewsList, setshowNewsList] = useState(false);
  const [newsCategory, setnewsCategory] = useState("Sports");

  const showNews = () => setshowNewsList(true);
  const changeCategory = (category) => setnewsCategory(category);

  if (!showNewsList) {
    return (
      <div className="App">
        <h1 className="app-title">PulseFeed News Terminal</h1>
        <p className="category-text">
          Selected Category: <span>{newsCategory}</span>
        </p>

        <DropdownButton
          id="dropdown-category"
          title="Choose News Category"
          onSelect={changeCategory}
          className="custom-dropdown"
        >
          <Dropdown.Item eventKey={"General"}>General</Dropdown.Item>
          <Dropdown.Item eventKey={"Sports"}>Sports</Dropdown.Item>
          <Dropdown.Item eventKey={"Technology"}>Science</Dropdown.Item>
          <Dropdown.Item eventKey={"Technology"}>Technology</Dropdown.Item>
          <Dropdown.Item eventKey={"Business"}>Business</Dropdown.Item>
          <Dropdown.Item eventKey={"Health"}>Health</Dropdown.Item>
          <Dropdown.Item eventKey={"Entertainment"}>
            Entertainment
          </Dropdown.Item>
        </DropdownButton>

        <button className="start-button" onClick={showNews}>
          🧠 Load News
        </button>
      </div>
    );
  }

  return (
    <NewsListScreen
      onBack={() => setshowNewsList(false)}
      newsCategory={newsCategory}
    />
  );
}

export default App;
