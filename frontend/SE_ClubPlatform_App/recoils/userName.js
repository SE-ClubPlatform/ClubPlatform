import {atom} from 'recoil';

const userName = atom({
  key: 'userName',
  default: '',
});

export default userName;
