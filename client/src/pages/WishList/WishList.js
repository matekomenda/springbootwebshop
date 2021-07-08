import React,{useEffect,useState} from "react";
import styled from "styled-components";
import img from "../../images/t-shirt.jpg";
import axios from 'axios';

const WishContainer = styled.div`
  min-height: 1100px;
`;
const WishWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-color: black;
  color: white;
`;
const WishlistTitle = styled.p`
  text-align: center;
  margin: 60px 0 30px 0;
  font-size: 36px;
  font-weight: 600;
  font-style: italic;
  color: white;
`;

const CartCardContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border: 1px solid black;
  margin: 1rem 0;
  width: 650px;
  background: white;
  color: black;
  border: 1px solid white;
  margin-bottom: 1rem;
`;

const Left = styled.div`
  width: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: red;
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


const WishList = () => {
  const [wishItems, setWishItems] = useState([]);
  const actWish = localStorage.getItem('wishlist');
  

  useEffect(() => {
    const AxiosGetWishItems =  async () => {
      const serverresponse = await axios.get(`http://localhost:8080/wishlist/products/${actWish}`);
      setWishItems(serverresponse.data);
      console.log(serverresponse.data);
    };
    AxiosGetWishItems();
  },[wishItems,actWish]);
  const handleProductDelete = async (e) => {
    const response = await axios.delete(`http://localhost:8080/wishlist/${actWish}/${e}`);
    console.log(response.data);
    window.alert("Product is successfully deleted from wishlist!");
  }

  const handleAddToCart= async () => {
    //const response = await axios.post(`http://localhost:8080/addToWishList/${actWishlist}/${product.id}`, {});
    //console.log(response.data);
    window.alert("Product successfully added to Cart!");
  }

  return (
    <WishContainer>Wishlist
      <WishWrapper>
        <WishlistTitle>My Wishlist</WishlistTitle>
        
        
         {wishItems.map((wishItem, index) => (
          <CartCardContainer key={index}>
            <Left>
              <MyImage src={img} />
            </Left>
            <Right>
              <RightUp>
                <ProductName>{wishItem.name}</ProductName>
                <ProductPrice>Price: {wishItem.price}$</ProductPrice>
              </RightUp>
              <RightDown>
                <DeleteButton onClick={e => handleProductDelete(wishItem.id)} >
                  Remove from wishlist
                </DeleteButton>
                <AddToWishList onClick={handleAddToCart}>Add to Cart</AddToWishList>
              </RightDown>
            </Right>
          </CartCardContainer>
        ))}
        
      </WishWrapper>
      
    </WishContainer>
  );
};

export default WishList;


