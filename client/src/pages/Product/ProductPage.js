import React, { useState, useEffect } from "react";
import styled from "styled-components";
import img from "../../images/t-shirt.jpg";
import axios from "axios";

const ProductPageContainer = styled.div`
  height: 100vh;
  background-color: #444;
`;

const ProductPageWrapper = styled.div`
  width: 80%;
  margin: 20px auto;
  padding: 60px 0;
  position: relative;
`;

const ProductPageImage = styled.div`
  float: right;
  width: 400px;
  height: 500px;
  background: url(${img}) no-repeat center center;
  border-radius: 8px;
`;

const ProductTitle = styled.div`
  font-size: 36px;
  font-weight: 600;
  margin-bottom: 30px;
  color: white;
`;

const ProductPageData = styled.div`
  font-size: 30px;
  margin-bottom: 20px;
  color: white;
`;

const ProductPage = (e) => {
  const [product, setProduct] = useState({});
  const [category, setCategory] = useState("");

  useEffect(() => {
    const AxiosGetProduct = async () => {
      const serverresponse = await axios.get(
        `http://localhost:8080/productById/${e.location.state.id}`
      );
      setProduct(serverresponse.data);
      setCategory(serverresponse.data.category.name);
    };

    AxiosGetProduct();
  }, []);

  return (
    <ProductPageContainer>
      <ProductPageWrapper>
        <ProductPageImage />
        <ProductTitle>{product.name}</ProductTitle>
        <ProductPageData>Price: {product.price}$</ProductPageData>
        <ProductPageData>Quantity: {product.quantity}</ProductPageData>
        <ProductPageData>Category: {category}</ProductPageData>
        <ProductPageData>Description: {product.description}</ProductPageData>
      </ProductPageWrapper>
    </ProductPageContainer>
  );
};

export default ProductPage;
