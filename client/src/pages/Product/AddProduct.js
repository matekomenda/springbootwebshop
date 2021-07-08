import React, { useState, useEffect } from "react";
import styled from "styled-components";
import axios from "axios";

const AddPrdctContainer = styled.div`
  height: 100vh;
  background: black;
  color: white;
`;
const AddPrdctwrapper = styled.div`
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
const AddForm = styled.form`
  background: black;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  height: 700px;
  width: 1000px;
  color: white;
`;
const TopContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
`;
const BottomContainer = styled.div`
  display: flex;
  flex-direction: column;
`;
const Header1 = styled.h1``;
const MyInput = styled.input`
  margin: 1rem 0;
  background: none;
  color: purple;
  background: white;
  font-size: 16px;
  padding: 10px 10px 10px 15px;
  display: block;
  width: 320px;
  border: none;
  border-radius: 10px;
  border: 1px solid white;

  &:active {
    outline: none;
  }
`;
const MySelect = styled.select`
  margin: 1rem 0;
  background: white;
  color: black;
  font-size: 16px;
  padding: 10px 10px 10px 15px;
  width: 320px;
  border: none;
  border-radius: 10px;
  border: 1px solid white;
`;
const ButtonContainer = styled.div``;
const MyButton = styled.button`
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
  color: #fff;
  font-size: 20px;
  background: #0f0c24;
  color: palegoldenrod;
`;

const AddProduct = () => {
  const [productData, setProductData] = useState({
    name: "",
    price: "",
    quantity: "",
    category: "",
    picture: "",
    description: "",
  });
  const [categories, setCategories] = useState([]);

  const Add = categories.map((Add) => Add);

  const handleCategories = (e) => {
    setProductData({ ...setProductData, category: e.target.value });
  };

  useEffect(() => {
    const fetchCategories = async () => {
      const respone = await axios.get(`http://localhost:8080/categories`);
      setCategories(respone.data);
    };
    fetchCategories();
  }, []);

  const handleSubmit = async () => {
    const newProduct = {
      name: productData.name,
      price: productData.price,
      quantity: productData.quantity,
      picture: productData.picture,
      description: productData.description,
    };

    try {
      const config = {
        headers: {
          "Content-Type": "application/json",
        },
      };
      const body = JSON.stringify(newProduct);
      const response = await axios.post(
        `http://localhost:8080/addProduct/12`,
        body,
        config
      );
      console.log(response.data);
      clear();
      window.alert("Product has been added");
    } catch (error) {
      console.error(error.response.data);
      window.alert("Something went wrong");
    }
  };

  const clear = () => {
    setProductData({
      name: "",
      price: "",
      quantity: "",
      category: "",
      picture: "",
      description: "",
    });
  };
  return (
    <AddPrdctContainer>
      <AddPrdctwrapper>
        <AddForm>
          <TopContainer>
            <Header1>Add Product</Header1>
          </TopContainer>
          <BottomContainer>
            <MyInput
              type="text"
              placeholder="Name"
              value={productData.name}
              onChange={(e) =>
                setProductData({ ...productData, name: e.target.value })
              }
              required
            />
            <MyInput
              type="number"
              placeholder="Price $"
              value={productData.price}
              onChange={(e) =>
                setProductData({ ...productData, price: e.target.value })
              }
            />
            <MyInput
              type="number"
              placeholder="Quantity"
              value={productData.quantity}
              onChange={(e) =>
                setProductData({ ...productData, quantity: e.target.value })
              }
            />
            <MySelect onChange={(e) => handleCategories(e)}>
              {Add.map((category, key) => (
                <option key={key} value={key}>
                  {category.name}
                </option>
              ))}
            </MySelect>
            <MyInput
              type="text"
              placeholder="Description"
              value={productData.description}
              onChange={(e) =>
                setProductData({ ...productData, description: e.target.value })
              }
            />
          </BottomContainer>
          <ButtonContainer>
            <MyButton onClick={handleSubmit}>Save</MyButton>
          </ButtonContainer>
        </AddForm>
      </AddPrdctwrapper>
    </AddPrdctContainer>
  );
};

export default AddProduct;
/*
 <MySelect
              name="category"
              id="category"
              placeholder="Category"
              defaultValue=""
              value={productData.category} 
                onChange={(e) => setProductData({...productData, category: e.target.value})}
            >
              <option value="">Select Categoryasd</option>
              <option value="Tops">Tops</option>
              <option value="Bottoms">Bottoms</option>
              <option value="JacketCoat">Jacket&Coat</option>
              <option value="Etc">Etc</option>
            </MySelect>
*/
