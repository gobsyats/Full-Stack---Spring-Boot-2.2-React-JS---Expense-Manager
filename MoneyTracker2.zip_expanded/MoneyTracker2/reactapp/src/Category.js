import React, { Component } from "react";
import AppNav from "./AppNav";

class Category extends Component {
  //Internal Storage of any component
  //like private fields in JAVA
  state = {
    //once you load
    isloading: true,
    //declare array
    Categories: []
  };

  //async calls
  //send request, dont wait
  async componentDidMount() {
    const response = await fetch("/api/categories");
    const body = await response.json();
    this.setState({ Categories: body, isloading: false });
  }

  //Incharge of processing JSX file
  //Return as an export
  render() {
    const { Categories, isloading } = this.state;
    if (isloading) return <div>loading...</div>;

    return (
      <div>
        <AppNav />
        <h2>Categories</h2>
        {Categories.map(categoryNext => (
          <div id={categoryNext.catId}>{categoryNext.catName}</div>
        ))}
      </div>
    );
  }
}

//Internal Storage of any component
//like private fields in JAVA
export default Category;
