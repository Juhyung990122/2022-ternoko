import { Link } from 'react-router-dom';

import Reservation from '../../components/Reservation';
import Button from '../../components/@common/Button/styled';
import GridContainer from '../../components/@common/GridContainer/styled';
import * as S from './styled';

const HomePage = () => {
  return (
    <>
      <S.TitleBox>
        <h2>나의 면담</h2>
        <Link to="/reservation/apply">
          <Button>+ 신청하기</Button>
        </Link>
      </S.TitleBox>
      <S.TabMenuBox>
        <h3>진행중 면담</h3>
        <h3>완료한 면담</h3>
      </S.TabMenuBox>
      <GridContainer minSize="25rem" pt="4rem">
        <Reservation />
        <Reservation />
        <Reservation />
        <Reservation />
      </GridContainer>
    </>
  );
};

export default HomePage;
