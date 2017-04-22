import React, {Component} from "react";

// class controlled component - has it's value set by state
//
class SearchBar extends Component {

  // create a new state entity
  constructor(props) {
    super(props);
    this.state = { term: ''};
  }

  render () {
    // create a new handler for 'onChange' events. Call the 'onInputChange' method.
    return (
      <div className="search-bar">
        <input
          value={this.state.term}
          onChange={event => this.onInputChange(event.target.value)} />
      </div>
    )
  }

  onInputChange(term) {
    console.log("the term is : " + term);
    this.setState({term});
    this.props.onSearchTermChange(term);
  }

}


export default SearchBar;
