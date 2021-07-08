import React, { useState } from "react";
import styled from "styled-components";
import axios from "axios";
import { useHistory } from "react-router";

const CheckoutContainer = styled.div``;

const CheckoutWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-color: white;
  color: black;
`;

const CheckoutTitle = styled.p`
  text-align: center;
  margin: 60px 0 30px 0;
  font-size: 36px;
  font-weight: 600;
  font-style: italic;
  color: black;
`;

const CheckoutCard = styled.div`
  background: #7d7c7a;
  position: relative;
  overflow: hidden;
  margin: 0 0 20px;
  width: 60%;
  border: 0px solid;
  box-shadow: 2px 2px 2px #bbc6cc;
  padding: 30px 25px 20px 25px;
  justify-content: space-between;
  flex-direction: column;
  display: flex;
  border-radius: 15px;
`;

const CheckoutCardTitle = styled.div`
  font-size: 22px;
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

const ButtonHolder = styled.div`
  justify-content: flex-end;
  display: flex;
`;

const NiceButton = styled.button`
  background-color: #2f456f;
  margin: 10px;
  padding: 15px 45px;
  text-align: center;
  text-transform: uppercase;
  transition: 0.2s;
  background-size: 200% auto;
  color: white;
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

const Checkout = () => {
  const user = localStorage.getItem("actualUser");
  let history = useHistory();

  const [formData, setFormData] = useState({
    country: "",
    zipcode: "",
    city: "",
    address: "",
    number: "",
    message: "",
  });

  const handlePurchase = async () => {
    const newFormData = {
      country: formData.country,
      zipcode: formData.zipcode,
      city: formData.city,
      address: formData.address,
      number: formData.number,
      message: formData.message,
    };

    try {
      const config = {
        headers: {
          "Content-Type": "application/json",
        },
      };
      const body = JSON.stringify(newFormData);
      const response = await axios.post(
        `http://localhost:8080/createOrder/${user}`,
        body,
        config
      );
      console.log(response.data);
      clear();
      history.push("/");
      window.alert("The order was successful!");
    } catch (error) {
      console.error(error.response.data);
    }
  };

  const clear = () => {
    setFormData({
      country: "",
      zipcode: "",
      city: "",
      address: "",
      number: "",
      message: "",
    });
  };

  return (
    <CheckoutContainer>
      Checkout
      <CheckoutWrapper>
        <CheckoutTitle>Checkout</CheckoutTitle>
        <CheckoutCard>
          <CheckoutCardTitle>Country:</CheckoutCardTitle>
          <InputStyle
            type="text"
            value={formData.country}
            onChange={(e) =>
              setFormData({ ...formData, country: e.target.value })
            }
            required
          />
          <CheckoutCardTitle>Zipcode:</CheckoutCardTitle>
          <InputStyle
            type="number"
            value={formData.zipcode}
            onChange={(e) =>
              setFormData({ ...formData, zipcode: e.target.value })
            }
            required
          />
          <CheckoutCardTitle>City:</CheckoutCardTitle>
          <InputStyle
            type="text"
            value={formData.city}
            onChange={(e) => setFormData({ ...formData, city: e.target.value })}
            required
          />
          <CheckoutCardTitle>Address:</CheckoutCardTitle>
          <InputStyle
            type="text"
            value={formData.address}
            onChange={(e) =>
              setFormData({ ...formData, address: e.target.value })
            }
            required
          />
          <CheckoutCardTitle>Number:</CheckoutCardTitle>
          <InputStyle
            type="number"
            value={formData.number}
            onChange={(e) =>
              setFormData({ ...formData, number: e.target.value })
            }
            required
          />
          <CheckoutCardTitle>Message:</CheckoutCardTitle>
          <InputStyle
            type="text"
            value={formData.message}
            onChange={(e) =>
              setFormData({ ...formData, message: e.target.value })
            }
            required
          />
          <ButtonHolder>
            <NiceButton to="/login" onClick={handlePurchase}>
              Purchase
            </NiceButton>
          </ButtonHolder>
        </CheckoutCard>
      </CheckoutWrapper>
    </CheckoutContainer>
  );
};

export default Checkout;
