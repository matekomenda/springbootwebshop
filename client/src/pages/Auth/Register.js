import React, { useState } from "react";
import styled from "styled-components";
import axios from "axios";

const ButtonHolder = styled.div`
  justify-content: center;
  display: flex;
`;
const NiceButton = styled.button`
  background: white;
  margin: 10px;
  padding: 15px 45px;
  text-align: center;
  text-transform: uppercase;
  transition: 0.2s;
  background-size: 200% auto;
  color: black;
  box-shadow: 0 0 20px #eee;
  box-shadow: 5px 20px 30px rgba(0, 0, 0, 0.2);
  border-radius: 15px;
  &:hover {
    transform: scale(1);
    box-shadow: 0 0 20px #eee;
    cursor: pointer;
  }
`;
const RegContainer = styled.div`
  height: 80vh;
`;
const RegisterWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-color: white;
  color: black;
`;
const RegisterTitle = styled.p`
  text-align: center;
  margin: 60px 0 30px 0;
  font-size: 36px;
  font-weight: 600;
  font-style: italic;
  color: black;
`;
const RegisterCardTitle = styled.div`
  font-size: 14px;
  font-weight: 500;
  color: white;
  margin-bottom: 10px;
`;
const InputStyle = styled.input`
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-sizing: border-box;
`;
const RegisterCard = styled.div`
  background-color: #0f0c24;
  position: relative;
  overflow: hidden;
  margin: 0 0 20px;
  width: 30%;
  height: 450px;
  border: 0px solid;
  box-shadow: 2px 2px 2px #bbc6cc;
  padding: 30px 25px 20px 25px;
  justify-content: space-between;
  flex-direction: column;
  display: flex;
  border-radius: 15px;
`;

const Register = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    password2: "",
  });

  const handleSubmit = async () => {
    if (formData.password === formData.password2) {
      const newFormData = {
        name: formData.name,
        email: formData.email,
        password: formData.password,
      };

      try {
        const config = {
          headers: {
            "Content-Type": "application/json",
          },
        };
        const body = JSON.stringify(newFormData);
        const response = await axios.post(
          `http://localhost:8080/register`,
          body,
          config
        );
        console.log(response.data);
        clear();
        window.alert("User succesfully created");
      } catch (error) {
        console.error(error.response.data);
      }
    } else {
      window.alert("Passwords do not match");
    }
  };

  const clear = () => {
    setFormData({ name: "", email: "", password: "", password2: "" });
  };

  return (
    <RegContainer>
      Register
      <RegisterWrapper>
        <RegisterTitle>Register</RegisterTitle>
        <RegisterCard>
          <RegisterCardTitle>Name:</RegisterCardTitle>
          <InputStyle
            type="text"
            value={formData.name}
            onChange={(e) => setFormData({ ...formData, name: e.target.value })}
            required
          />
          <RegisterCardTitle>Email:</RegisterCardTitle>
          <InputStyle
            type="email"
            value={formData.email}
            onChange={(e) =>
              setFormData({ ...formData, email: e.target.value })
            }
            required
          />
          <RegisterCardTitle>Password:</RegisterCardTitle>
          <InputStyle
            type="password"
            value={formData.password}
            onChange={(e) =>
              setFormData({ ...formData, password: e.target.value })
            }
            required
          />
          <RegisterCardTitle>Verify Password:</RegisterCardTitle>
          <InputStyle
            type="password"
            value={formData.password2}
            onChange={(e) =>
              setFormData({ ...formData, password2: e.target.value })
            }
            required
          />
          <ButtonHolder>
            <NiceButton to="/register" onClick={handleSubmit}>
              Register
            </NiceButton>
          </ButtonHolder>
        </RegisterCard>
      </RegisterWrapper>
    </RegContainer>
  );
};

export default Register;
