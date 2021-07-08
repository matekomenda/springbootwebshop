import React from "react";
import styled from "styled-components";
import img from "../../images/t-shirt.jpg";
import { AiOutlineShoppingCart } from "react-icons/ai";
import { AiOutlineHeart } from "react-icons/ai";
import axios from "axios";
import { useHistory } from "react-router-dom";

const CardContainer = styled.div`
  width: 300px;
  height: 500px;
  background: white;
  margin: auto;
  position: relative;
  overflow: hidden;
  border-radius: 10px 10px 10px 10px;
  box-shadow: 0;
  transform: scale(0.95);
  transition: box-shadow 0.5s, transform 0.5s;
  color: #edf7d2;
  &:hover {
    transform: scale(1);
    box-shadow: 5px 20px 30px rgba(0, 0, 0, 0.2);
    cursor: pointer;
  }
`;
const CardWrapper = styled.div`
  width: 100%;
  height: 100%;
`;
const Top = styled.div`
  height: 80%;
  width: 100%;
  background: url(${img}) no-repeat center center;
  background-size: 100%;
`;
const Bottom = styled.div`
  width: 100%;
  height: 20%;
  transition: transform 0.5s;
  display: flex;
  flex-direction: row;
  align-items: center;
`;
const BottomLeft = styled.div`
  height: 100%;
  width: 50%;
  background-color: #0f0c24;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;
const BottomRight = styled.div`
  height: 100%;
  width: 50%;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  background-color: #0f0c24;
  color: white;
`;
const Details = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
`;
const Name = styled.h3`
  margin: 0;
  padding: 0 0 0 15px;
`;
const Price = styled.p`
  margin: 0;
  padding: 0 0 0 15px;
`;

const ShoppingCart = styled(AiOutlineShoppingCart)`
  width: 50px;
  height: 50px;
  color: #f8e71c;
  cursor: pointer;
  padding: 10px;
  margin-right: 1rem;
  user-select: none;
  transition: 0.3s;

  &:hover {
    color: #edf7d2;
  }
`;

const WishList = styled(AiOutlineHeart)`
  width: 50px;
  height: 50px;
  color: #e9a8c3;
  cursor: pointer;
  padding: 10px;
  margin-right: 1rem;
  user-select: none;
  transition: 0.3s;

  &:hover {
    color: #edf7d2;
  }
`;

const ProductCard = ({ product }) => {
  const actCart = localStorage.getItem("actCart");
  const actWishlist = localStorage.getItem("wishlist");

  /*const handleSubmit = async () => {
    const responseW = await axios.post(`http://localhost:8080/wishlist/${actCart}/${product.id}`, {});
    window.alert("Product has been added to the wishlist");
    console.log("Product has been added to the wishlist" + responseW.data);
  }*/
  const handleAddToCart = async () => {
    const response = await axios.post(
      `http://localhost:8080/addToCart/${actCart}/${product.id}`,
      {}
    );
    console.log(response.data);
    window.alert("Product successfully added to cart!");
  };
  const handleAddToWishlist = async () => {
    const response = await axios.post(
      `http://localhost:8080/addToWishList/${actWishlist}/${product.id}`,
      {}
    );
    console.log(response.data);
    window.alert("Product successfully added to wishlist!");
  };

  let history = useHistory();

  const handleNameClick = (e) => {
    history.push({
      pathname: "/product",
      state: {
        id: e,
      },
    });
  };

  return (
    <CardContainer>
      <CardWrapper>
        <Top onClick={(e) => handleNameClick(product.productId)}></Top>
        <Bottom>
          <BottomLeft>
            <Details>
              <Name>{product.name}</Name>
              <Price>{product.price}$</Price>
            </Details>
          </BottomLeft>
          <BottomRight>
            <ShoppingCart onClick={handleAddToCart} />
            <WishList onClick={handleAddToWishlist} />
          </BottomRight>
        </Bottom>
      </CardWrapper>
    </CardContainer>
  );
};

export default ProductCard;
