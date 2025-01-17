import * as S from './styled';

import Loading from '@/components/@common/Loading';

const TernokoLoading = () => {
  return (
    <Loading
      additionalBoxStyle={S.additionalBoxStyle}
      profileSizeRem={25}
      animationDuration={1.2}
    />
  );
};

export default TernokoLoading;
