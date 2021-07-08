import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

const Nav = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 999;
  width: 100%;
  height: 60px;
  position: fixed;
  top: 0;
  left: 0;
  background-color: #0F0C24;
`;
const NavLogo = styled(Link)`
  color: #EDF7B5;
  display: flex;
  align-items: center;
  padding: 0 1rem;
  height: 100%auto;
  cursor: pointer;
  text-decoration: none;
  font-style: italic;
  flex: 1;
  margin-left: 25px;
  text-transform: uppercase;
  font-weight: 700;
  font-size: 32px;
`;
const NavMenu = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-start;
`;
const NavMenuLink = styled(Link)`
  color: #EDF7B5;
  display: flex;
  align-items: center;
  padding: 0 1rem;
  height: 100%;
  cursor: pointer;
  text-decoration: none;
  height: 60px;
  &:hover {
    background-color: #575465;
    color: white;
    border-bottom: 4px solid white;
    cursor: pointer;
  }
`;

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const user = localStorage.getItem("actualUser");
  useEffect(() => {
    if(localStorage.getItem('actualUser') || user) {
      setIsLoggedIn(true);
      
    } else {
      setIsLoggedIn(false);
    }
    console.log(user);
  }, [user]);

  const handleLogout = () => {
    localStorage.clear();
  }

  return (
    <Nav>
      <NavLogo to="/">
        {/*react icon */}
        Webshop
      </NavLogo>
      <NavMenu>
        <NavMenuLink to="/products">Products</NavMenuLink>
        <NavMenuLink to="/cart">Cart</NavMenuLink>
        <NavMenuLink to="/wishlist">WishList</NavMenuLink>
        {isLoggedIn 
        ?
        <NavMenuLink to="/" onClick={handleLogout}>
          Logout
        </NavMenuLink> 
        :
        <NavMenuLink to ="/login">
             LogIn
        </NavMenuLink> 
        
         }
        {isLoggedIn 
        ?
        <React.Fragment></React.Fragment>
        :
        <NavMenuLink to ="/register">
             Register
        </NavMenuLink> 
        
         }
      </NavMenu>
    </Nav>
  );
};

export default Navbar;
