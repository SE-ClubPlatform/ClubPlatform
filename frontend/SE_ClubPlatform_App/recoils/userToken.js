import {atom} from 'recoil';

const userToken = atom({
  key: 'userToken',
  default: '',
});

export default userToken;
