import { nameSanityCheck, passwordSanityCheck, checkReEnteredPasswordValidation, checkReEnteredEmailValidation} from "../component/SignUp";



test("nameSanityCheck should return true for valid names -null", ()=>{
    expect(nameSanityCheck("")).toBe(false);
});

test("nameSanityCheck should return true for valid names -9ine", ()=>{
    expect(nameSanityCheck("9ine")).toBe(false);
});

test("nameSanityCheck should return true for valid names -haidari_m'", ()=>{
    expect(nameSanityCheck("haidari_m")).toBe(false);
});

test("nameSanityCheck should return true for valid names -haidari/m", ()=>{
    expect(nameSanityCheck("haidari/m")).toBe(false);
});

test("nameSanityCheck should return true for valid names -haidari&m", ()=>{
    expect(nameSanityCheck("haidari&m")).toBe(false);
});

test("nameSanityCheck should return true for valid names -{haidarim", ()=>{
    expect(nameSanityCheck("{haidarim")).toBe(false);
});

test("nameSanityCheck should return true for valid names -haidarim%", ()=>{
    expect(nameSanityCheck("haidarim%")).toBe(false);
});


test("nameSanityCheck should return true for valid names -ha;darim", ()=>{
    expect(nameSanityCheck("{ha;darim")).toBe(false);
});

test('nameSanityCheck should return true for valid names -null', ()=>{
    expect(nameSanityCheck("makaveli")).toBe(true);
});

test('nameSanityCheck should return true for valid names -null', ()=>{
    expect(nameSanityCheck("makaveli5")).toBe(true);
});