package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GoodsCommandValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    } //정의할 필요 없음

    @Override
    public void validate(Object target, Errors errors) {
    //target은 goodsCommand
        ValidationUtils.rejectIfEmpty(errors,"prodName","required");
        //상품이름 유효성검사 후 에러발생시 reqired 에러를 띄워줌
        ValidationUtils.rejectIfEmpty(errors,"prodPrice","required");
        ValidationUtils.rejectIfEmpty(errors,"prodCapacity","required");
        ValidationUtils.rejectIfEmpty(errors,"prodSupplyer","required");
        ValidationUtils.rejectIfEmpty(errors,"prodDelFee","required");
        ValidationUtils.rejectIfEmpty(errors,"recommend","required");
        ValidationUtils.rejectIfEmpty(errors,"prodDetail","required");
    }
}
