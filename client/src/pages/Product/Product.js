import React, { useEffect, useState } from "react";
import styled from "styled-components";
import ProductCard from "../Product/ProductCard";
import axios from "axios";

const ProductContainer = styled.div`
  height: 100%;
`;
const ProductWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: center;
  //background-color: #444;
  //background-color: #FFE066;
  background-color: white;
  margin-top: 70px;
`;
const ProductsGridContainer = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  align-items: center;
  justify-content: center;
  grid-gap: 80px;
  max-width: 1750px;
`;

const Product = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const AxiosGetProducts = async () => {
      const serverresponse = await axios.get(`http://localhost:8080/products`);
      setProducts(serverresponse.data);
      console.log(serverresponse.data);
    };
    AxiosGetProducts();
  }, []);

  return (
    <ProductContainer>
      <ProductWrapper>
        {/*<TopContainer>Search etc..</TopContainer>*/}
        <ProductsGridContainer>
          {products.map((product, index) => (
            <ProductCard key={product.id} product={product} />
          ))}
        </ProductsGridContainer>
      </ProductWrapper>
    </ProductContainer>
  );
};

export default Product;
