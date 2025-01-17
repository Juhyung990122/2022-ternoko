import styled from 'styled-components';

import theme from '@/styles/theme';

export const Icon = styled.img`
  position: absolute;
  top: 1rem;
  right: 1rem;
  width: 2rem;
  height: 2rem;
  object-fit: cover;
  cursor: pointer;
`;

export const ExampleBox = styled.div`
  margin: 1rem 0 3rem;
  color: ${({ theme }) => theme.colors.gray_200};

  > p {
    line-height: 1.5;
  }
`;

export const ButtonBox = styled.div`
  margin-top: 1.5rem;
`;

export const Form = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
`;

export const additionalDimmerStyle = `
  z-index: 2;
`;

export const additionalFrameStyle = `
  @media ${theme.devices.laptop(200)} {
    width: 60%;
  };

  @media ${theme.devices.tablet()} {
    width: 70%;
  };

  @media ${theme.devices.tabletM()} {
    width: 90%;
  };
`;
