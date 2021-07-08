import React from "react";
import styled from "styled-components";
import landingpic from "../../images/landingpic.jpg";
import { Link } from "react-router-dom";

const HomeContainer = styled.div`
  height: 100vh;
`;
const HomeWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-color: #444;
  color: white;
`;
const HomeImage = styled.img`
  position: absolute;
  top: 0;
  left: 0;
  width: 99.1vw;
  height: 100vh;
  object-fit: cover;
  overflow-x: hidden;
  overflow: hidden;
`;

const HomeContent = styled.div`
  position: relative;
  z-index: 10;
  display: flex;
  flex-direction: column;
  max-width: 1600px;
  width: calc(100% - 100px);
  color: white;

  h1 {
    font-size: clamp(1rem, 8vw, 2rem);
    font-weight: 400;
    text-transform: uppercase;
    text-shadow: 1px 1px 2px black, 0 0 25px yellow, 0 0 5px yellow;
    text-align: left;
    margin-bottom: 0.8rem;
  }

  p {
    margin-bottom: 1.2rem;
    text-shadow: 1px 1px 2px black, 0 0 25px yellow, 0 0 5px yellow;
  }
`;

const MyButton = styled(Link)`
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
  background-color: #0f0c24;
  color: palegoldenrod;

  &:hover {
    transform: scale(1);
    box-shadow: 0 0 20px #eee;
    cursor: pointer;
  }
`;

const Home = () => {
  return (
    <HomeContainer>
      <HomeWrapper>
        <HomeImage src={landingpic} alt={"landingpic"} />
        <HomeContent>
          <h1>Clothes That Look The Part, Wherever you go</h1>
          <p>Introducing the explorer quicdraw ruckstack</p>
          <MyButton to="/products">Shop Now</MyButton>
        </HomeContent>
      </HomeWrapper>
    </HomeContainer>
  );
};

export default Home;
