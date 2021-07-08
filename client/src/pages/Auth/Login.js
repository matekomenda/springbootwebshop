import React, { useState } from "react";
import styled from "styled-components";
import axios from "axios";

const NiceButton = styled.button`
  //background: linear-gradient(to right, #194147 0%, #194147  51%, #194147  100%);
  background-color: white;
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
  display: block;
  &:hover {
    transform: scale(1);
    box-shadow: 0 0 20px #eee;
    cursor: pointer;
  }
`;

const LogContainer = styled.div`
  //height: 100vh;
  height: 80vh;
  //overflow: hidden;
`;

const LoginWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  //background-color: #444;
  background-color: white;
  color: black;
`;

const LoginTitle = styled.p`
  text-align: center;
  margin: 60px 0 30px 0;
  font-size: 36px;
  font-weight: 600;
  font-style: italic;
  color: black;
`;

const ButtonHolder = styled.div`
  justify-content: center;
  display: flex;
`;

const LoginCard = styled.div`
  background-color: #0f0c24;
  position: relative;
  overflow: hidden;
  width: 500px;
  height: 350px;
  justify-content: space-between;
  flex-direction: column;
  display: flex;
  border: 0px solid;
  box-shadow: 2px 2px 2px #bbc6cc;
  padding: 30px 25px 20px 25px;
  border-radius: 15px;
`;

const LoginCardTitle = styled.div`
  font-size: 14px;
  font-weight: 500;
  color: white;
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

const Login = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleSubmit = async () => {
    const newFormData = {
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
        `http://localhost:8080/mylogin`,
        body,
        config
      );
      console.log(response.data);
      localStorage.setItem("actualUser", response.data.userId);
      localStorage.setItem("actCart", response.data.cart.cartId);
      localStorage.setItem("wishlist", response.data.wishlist.wishlistId);
      clear();
      window.alert("Successful Login");
    } catch (error) {
      console.error(error.response.data);
    }
  };

  const clear = () => {
    setFormData({ email: "", password: "" });
  };

  return (
    <LogContainer>
      Login
      <LoginWrapper>
        <LoginTitle>Login</LoginTitle>
        <LoginCard>
          <LoginCardTitle>Email:</LoginCardTitle>
          <InputStyle
            type="email"
            value={formData.email}
            onChange={(e) =>
              setFormData({ ...formData, email: e.target.value })
            }
            required
          />
          <LoginCardTitle>Password:</LoginCardTitle>
          <InputStyle
            type="password"
            value={formData.password}
            onChange={(e) =>
              setFormData({ ...formData, password: e.target.value })
            }
            required
          />
          <ButtonHolder>
            <NiceButton to="/login" onClick={handleSubmit}>
              Login
            </NiceButton>
          </ButtonHolder>
        </LoginCard>
      </LoginWrapper>
    </LogContainer>
  );
};

export default Login;
