import React, { Component } from "react";
import AppNav from "./AppNav";
import Moment from "react-moment";
import DatePicker from "react-datepicker";
import "./App.css";
import "react-datepicker/dist/react-datepicker.css";
import {
  Table,
  Container,
  FormGroup,
  Form,
  Input,
  Button,
  Label
} from "reactstrap";
import { Link } from "react-router-dom";

class Expenses extends Component {
  emptyItem = {
    expId: 7,
    dateTime: new Date(),
    descrp: "",
    location: "",
    category: [1, "Food2"]
  };

  constructor(props) {
    super(props);
    this.state = {
      date: new Date(),
      isloading: false,
      expenses: [],
      categories: [],
      item: this.emptyItem
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.handleDateChange = this.handleDateChange.bind(this);
  }

  async remove(expId) {
    await fetch(`api/expenses/${expId}`, {
      method: "DELETE",
      headers: {
        Accept: "application",
        "Content-Type": "application/json"
      }
    }).then(() => {
      let updatedExpenses = [...this.state.expenses].filter(
        i => i.expId !== expId
      );
      this.setState({ expenses: updatedExpenses });
    });
  }

  async componentDidMount() {
    const response = await fetch("/api/categories");
    const body = await response.json();
    this.setState({ categories: body, isloading: false });

    const responseExp = await fetch("/api/expenses");
    const bodyExp = await responseExp.json();
    this.setState({ expenses: bodyExp, isloading: false });
  }

  async handleSubmit(event) {
    const item = this.state.item;
    await fetch(`api/expenses`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(item)
    });
    event.peventDefault();
    this.props.history.push("/expenses");
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = { ...this.state.item };
    item[name] = value;
    this.setState(item);
    console.log(this.state.item);
  }

  handleDateChange(date) {
    let item = { ...this.state.item };
    item.dateTime = date;
    this.setState({ item });
  }

  render() {
    const title = <h3>Add Expenses</h3>;
    const { categories } = this.state;
    const { expenses, isloading } = this.state;

    if (isloading) return <div>Loading...</div>;

    let optionList = categories.map(category => (
      <option id={category.catId}> {category.catName}</option>
    ));

    let rows = expenses.map(exp => (
      <tr key={exp.expId}>
        <td>{exp.descrp}</td>
        <td>{exp.location}</td>
        <td>
          <Moment date={exp.dateTime} format="YYYY/MM/DD" />
        </td>
        <td>{exp.category.catName}</td>
        <td>
          <Button
            size="sm"
            color="danger"
            onClick={() => this.remove(exp.expId)}
          >
            {" "}
            Delete
          </Button>
        </td>
      </tr>
    ));

    return (
      <div>
        <AppNav />
        <h2>Expenses</h2>
        <Container>
          {title}
          <Form onSubmit={this.handleSubmit}>
            <FormGroup>
              <Label for="title">Title</Label>
              <Input
                type="text"
                name="title"
                id="title"
                onChange={this.handleChange}
                autoComplete="name"
              />
            </FormGroup>

            <FormGroup>
              <Label for="category">Category</Label>
              <select onChange={this.handleChange}>{optionList}</select>
            </FormGroup>

            <FormGroup>
              <Label for="expenseDate">Expense Date</Label>
              <DatePicker
                selected={this.state.item.dateTime}
                onChange={this.handleDateChange}
              />
            </FormGroup>

            <FormGroup>
              <Label for="title">Location</Label>
              <Input
                type="text"
                name="location"
                id="location"
                onChange={this.handleChange}
                autoComplete="location"
              />
            </FormGroup>

            <FormGroup>
              <Button color="primary" type="submit">
                Save
              </Button>{" "}
              <Button color="secondary" tag={Link} to="/categories">
                Cancel
              </Button>
            </FormGroup>
          </Form>
        </Container>
        {""}
        <Container>
          <h3>Expense List</h3>
          <Table className="mt-4">
            <thead>
              <tr>
                <th width="20%"> Description</th>
                <th width="10%"> Location</th>
                <th width="20%"> Date</th>
                <th>Category</th>
                <th width="10%">Action</th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
          </Table>
        </Container>
        }
      </div>
    );
  }
}

export default Expenses;
