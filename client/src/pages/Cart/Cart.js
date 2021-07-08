import React, { useState, useEffect } from "react";
import styled from "styled-components";
import img from "../../images/t-shirt.jpg";
import axios from "axios";
import { useHistory } from "react-router-dom";

const CartContainer = styled.div`
  min-height: 1000px;
`;
const CartWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-color: white;
  color: black;
`;

const CartTitle = styled.p`
  text-align: center;
  margin: 60px 0 30px 0;
  font-size: 36px;
  font-weight: 600;
  font-style: italic;
  color: black;
`;

const CartCardContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border: 1px solid black;
  margin: 1rem 0;
  width: 650px;
  background: black;
  color: white;
  border: 2px solid black;
`;

const Left = styled.div`
  width: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
const MyImage = styled.img`
  max-width: 100%;
  height: auto;
`;
const Right = styled.div`
  display: flex;
  width: 50%;
  height: 100%;
  flex-direction: column;
  justify-content: space-around;
  height: 485px;
`;
const RightUp = styled.div`
  display: flex;
  flex-direction: column;
`;
const ProductName = styled.h2`
  padding: 20px 20px;
`;
const ProductPrice = styled.h3`
  padding: 20px 20px;
`;
const RightDown = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  padding: 20px 20px;
`;
const DeleteButton = styled.button`
  background: red;
  margin: 10px 0;
  white-space: nowrap;
  outline: none;
  border: none;
  min-width: 100px;
  max-width: 200px;
  cursor: pointer;
  text-decoration: none;
  transition: 0.3s;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 16px 40px;
  color: white;
  font-size: 16px;
  &:hover {
    background: white;
    color: red;
  }
`;

const AddToWishList = styled.button`
  margin: 10px 0;
  background: green;
  white-space: nowrap;
  outline: none;
  border: none;
  min-width: 100px;
  max-width: 200px;
  cursor: pointer;
  text-decoration: none;
  transition: 0.3s;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 16px 40px;
  color: white;
  font-size: 16px;
  &:hover {
    background: white;
    color: green;
  }
`;

const SumContainer = styled.div`
  background-color: white;
  width: 35%;
  margin: 20px 0;
  border: 2px solid #7d7c7a;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: black;
  color: white;
`;
const SumUp = styled.div`
  padding: 10px 10px;
`;
const Total = styled.h3``;
const SumDown = styled.div`
  padding: 10px 10px;
`;
const CheckOutButton = styled.button`
  margin: 10px 0;
  background-color: #0f0c24;

  white-space: nowrap;
  outline: none;
  border: none;
  min-width: 100px;
  max-width: 200px;
  cursor: pointer;
  text-decoration: none;
  transition: 0.3s;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 16px 40px;
  color: white;
  font-size: 16px;
  &:hover {
    background: pink;
    color: #0f0c24;
  }
`;

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [cartTotal, setCartTotal] = useState(0);
  const actCart = localStorage.getItem("actCart");
  //const actWishlist = localStorage.getItem('wishlist');

  useEffect(() => {
    const AxiosGetCartItems = async () => {
      const serverresponse = await axios.get(
        `http://localhost:8080/cart/products/${actCart}`
      );
      setCartItems(serverresponse.data);
      console.log(cartItems);
    };
    const AxiosGetCartTotal = async () => {
      const response = await axios.get(`http://localhost:8080/cart/${actCart}`);
      setCartTotal(response.data.total);
      console.log(response.data.total);
    };

    AxiosGetCartItems();
    AxiosGetCartTotal();
  }, [cartItems]);

  const handleProductDelete = async (e) => {
    const response = await axios.delete(
      `http://localhost:8080/cart/${actCart}/${e}`
    );
    console.log(response.data);
    window.alert("Product is successfully deleted from cart!");
  };

  const handleAddToWishlist = async () => {
    //const response = await axios.post(`http://localhost:8080/addToWishList/${actWishlist}/${product.id}`, {});
    //console.log(response.data);
    window.alert("Product successfully added to wishlist!");
  };

  let history = useHistory();
  const handleContinue = () => {
    if (cartTotal !== 0) {
      history.push("/checkout");
    } else {
      window.alert("The cart is empty!");
    }
  };

  return (
    <CartContainer>
      Cart
      <CartWrapper>
        <CartTitle>My Cart</CartTitle>

        {cartItems.map((cartItem, index) => (
          <CartCardContainer key={index}>
            <Left>
              <MyImage src={img} />
            </Left>
            <Right>
              <RightUp>
                <ProductName>{cartItem.name}</ProductName>
                <ProductPrice>Price: {cartItem.price}$</ProductPrice>
              </RightUp>
              <RightDown>
                <DeleteButton onClick={(e) => handleProductDelete(cartItem.id)}>
                  Remove from cart
                </DeleteButton>
                <AddToWishList onClick={handleAddToWishlist}>
                  Add to Wishlist
                </AddToWishList>
              </RightDown>
            </Right>
          </CartCardContainer>
        ))}

        <SumContainer>
          <SumUp>
            <Total>Total price: {cartTotal.toFixed(2)}$</Total>
          </SumUp>
          <SumDown>
            <CheckOutButton onClick={handleContinue}>Checkout</CheckOutButton>
          </SumDown>
        </SumContainer>
      </CartWrapper>
    </CartContainer>
  );
};

export default Cart;
