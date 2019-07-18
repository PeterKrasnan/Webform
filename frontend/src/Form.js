import React, {Component} from 'react';
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem"

export default class Form extends Component {

    state = {
        requestTypes: [],
        requestType: "",
        policyNumber: "",
        policyNumberError: "",
        name: "",
        nameError: "",
        surname: "",
        surnameError: "",
        request: "",
        requestError: ""
    };

    componentDidMount() {
        fetch("http://localhost:9080/webform/api/requests")
            .then((response) => {
                return response.json();
            })
            .then(data => {
                let kindsOfRequestFromApi = data.map(kindOfRequest => {
                    return {value: kindOfRequest, display: kindOfRequest}
                });
                this.setState({
                    requestTypes: kindsOfRequestFromApi
                })
            }).catch(error => {
            console.log(error);
        });
    }

    change = event => {
        event.preventDefault();
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    validate = () => {
        let isError = false;

        if (this.state.policyNumber.length < 3 || !this.state.policyNumber.match(/^[A-Za-z0-9]+$/)) {
            isError = true;
            this.state.policyNumberError = "Policy number can contain only alphabetical characters and at least 3 characters";
        } else {
            this.state.policyNumberError = ""
        }

        if (this.state.name.length < 3 || !this.state.name.match(/^[A-Za-z]+$/)) {
            isError = true;
            this.state.nameError = "Name can contains only alphabetical characters and at least 3 characters";
        } else {
            this.state.nameError = ""
        }

        if (this.state.surname.length < 3 || !this.state.surname.match(/^[A-Za-z]+$/)) {
            isError = true;
            this.state.surnameError = "Surname can contains only alphabetical characters and at least 3 characters";
        } else {
            this.state.surnameError = ""
        }

        if (this.state.request.length < 3) {
            isError = true;
            this.state.requestError = "Request must contain at least 3 characters";
        } else {
            this.state.requestError = ""
        }

        //TODO: hotfix to rerender elements to show errors
        this.setState({});

        return isError;
    };

    onSubmit = (event) => {
        event.preventDefault();

        console.log("Has error: " + this.validate());
        if (!this.validate()) {

            let data = JSON.stringify({
                policyNumber: this.state.policyNumber,
                requestType: this.state.requestType,
                name: this.state.name,
                surname: this.state.surname,
                request: this.state.request
            });
            console.log("Data: " + data);

            fetch('http://localhost:9080/webform/create', {
                method: 'post',
                headers: {'Content-Type': 'application/json'},
                body: data
            });

            this.setState({
                name: "",
                nameError: "",
                surname: "",
                surnameError: "",
                policyNumber: "",
                policyNumberError: "",
                request: ""
            })
        }

    };

    style = {
        width: '50%',
        borderRadius: 3,
        border: 1,
        margin: 15
    };

    render() {
        return (
            <div style={this.style}>
                <h1> Contact Us </h1>
                <form>
                    <Select
                        style={this.style}
                        value={this.state.requestType}
                        onChange={(event) => this.setState({requestType: event.target.value})}>

                        {this.state.requestTypes.map((request) =>
                            <MenuItem style={this.style} key={request.value} value={request.value}>
                                {request.value}
                            </MenuItem>
                        )}
                    </Select>
                    <br/>

                    <TextField style={this.style}
                               name="policyNumber"
                               label="Policy Number"
                               value={this.state.policyNumber}
                               onChange={event => this.change(event)}
                               error={this.state.policyNumberError !== ""}
                               floatingLabelFixed
                               helperText={this.state.policyNumberError}
                    />
                    <br/>

                    <TextField style={this.style}
                               name="name"
                               label="Name"
                               value={this.state.name}
                               onChange={event => this.change(event)}
                               error={this.state.nameError !== ""}
                               floatingLabelFixed
                               helperText={this.state.nameError}
                    />
                    <br/>

                    <TextField style={this.style}
                               name="surname"
                               label="Surname"
                               value={this.state.surname}
                               onChange={event => this.change(event)}
                               error={this.state.surnameError !== ""}
                               floatingLabelFixed
                               helperText={this.state.surnameError}
                    />
                    <br/>

                    <TextField style={this.style}
                               name="request"
                               value={this.state.request}
                               label="Your request"
                               placeholder="Max 1000 characters"
                               multiline={true}
                               onChange={event => this.change(event)}
                               floatingLabelFixed
                               inputProps={{maxLength: 1000}}
                               error={this.state.requestError !== ""}
                               helperText={this.state.requestError}
                    />
                    <br/>

                    <Button style={this.style} onClick={e => this.onSubmit(e)}>
                        Submit
                    </Button>
                </form>
            </div>
        );
    }
}
