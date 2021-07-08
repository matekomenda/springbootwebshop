import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

const FooterContainer = styled.footer`
  background-color: #0F0C24;
  padding: 8rem 0 2rem 0;
  display: flex;
  flex-direction: column;
  color: #EDF7B5;
  font-family: "Poppins", sans-serif;
`;
const Top = styled.div`
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  text-align: center;
  margin-bottom: 50px;
`;
const TopRight = styled.div`
  ul {
    list-style: none;
  }
  li {
    text-align: left;
    padding: 0 0 12px 0px;
  }
`;
const Text = styled.h4`
  padding-bottom: 15px;
`;
const TopLeft = styled.div`
  ul {
    list-style: none;
  }
  li {
    text-align: left;
    padding: 0 0 12px 0px;
  }
`;

const NavLink = styled(Link)`
  color: #EDF7B5;
  display: flex;
  cursor: pointer;
  text-decoration: none;
  &:hover {
    color: white;
  }
`;

const Footer = () => {
  return (
    <FooterContainer>
      <Top>
        <TopRight>
          <Text>Webshop Menu</Text>
          <ul>
            <NavLink to="/products"><li>Products</li></NavLink>
            <NavLink to="/cart"><li>Cart</li></NavLink>
            <NavLink to="/wishlist"><li>WishList</li></NavLink>
          </ul>
        </TopRight>
        <TopLeft>
          <Text>Creators</Text>
          <ul>
            <li>Máté</li>
            <li>Péter</li>
            <li>Dávid</li>
          </ul>
        </TopLeft>
        <TopLeft>
          <Text>Creators</Text>
          <ul>
            <li>Máté</li>
            <li>Péter</li>
            <li>Dávid</li>
          </ul>
        </TopLeft>
        <TopLeft>
          <Text>Creators</Text>
          <ul>
            <li>Máté</li>
            <li>Péter</li>
            <li>Dávid</li>
          </ul>
        </TopLeft>
      </Top>
    </FooterContainer>
  );
};

export default Footer;
