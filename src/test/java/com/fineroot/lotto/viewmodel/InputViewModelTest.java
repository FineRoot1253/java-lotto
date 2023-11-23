package com.fineroot.lotto.viewmodel;

import static org.assertj.core.api.Assertions.assertThat;

import com.fineroot.lotto.dto.Money;
import com.fineroot.lotto.dto.WinningNumber;
import com.fineroot.lotto.domain.LottoNumber;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputViewModelTest {
    @Test
    @DisplayName("입력 금액 저장 및 확인")
    void saveMoney() {
        InputViewModel inputViewModel = new InputViewModel();
        inputViewModel.saveMoney(Money.from(1000));
        assertThat(inputViewModel.getMoney().getValue()).isEqualTo(1000);
    }

    @ParameterizedTest
    @DisplayName("당첨 번호 저장 및 확인")
    @CsvSource({"'1, 2, 3, 4, 5, 7',1,true", "'1, 2, 3, 4, 5, 6',7,false"})
    void saveWinningNumberAndBonusNumber(String dummy, int input, boolean expected) {
        String winNumber = "1, 2, 3, 4, 5, 6";
        InputViewModel inputViewModel = new InputViewModel();
        inputViewModel.saveWinningNumber(WinningNumber.from(winNumber));
        inputViewModel.saveBonusNumber(LottoNumber.from(7));
        assertThat(inputViewModel.getWinningNumberSet().contains(LottoNumber.from(input))).isEqualTo(expected);
        assertThat(inputViewModel.getWinningNumberSet().hasBonus(
                Arrays.stream(dummy.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumber::from)
                        .collect(Collectors.toList()))).isEqualTo(expected);
    }
}
