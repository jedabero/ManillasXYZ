package co.edu.cuc.jberdugo10.manillasxyz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private EditText mAmountText;

    private RadioGroup mMaterialGroup, mPendantGroup;

    private Spinner mTypesSpinner, mCurrencySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAmountText = (EditText) findViewById(R.id.amount);

        mMaterialGroup = (RadioGroup) findViewById(R.id.radio_group_material);
        mPendantGroup = (RadioGroup) findViewById(R.id.radio_group_pendant);

        mTypesSpinner = (Spinner) findViewById(R.id.spinner_type);
        mCurrencySpinner = (Spinner) findViewById(R.id.spinner_currency);

    }

    public void show(View view) {
        String amountText = mAmountText.getText().toString().trim();
        if (TextUtils.isEmpty(amountText)) {
            mAmountText.requestFocus();
            mAmountText.setError(getResources().getString(R.string.message_empty_field));
            return;
        }
        int amount;
        try {
            amount = Integer.parseInt(amountText);
        } catch (NumberFormatException nfe) {
            mAmountText.requestFocus();
            mAmountText.setError(getResources().getString(R.string.message_number_format));
            return;
        }

        int materialId = mMaterialGroup.getCheckedRadioButtonId();

        int pendantId  = mPendantGroup.getCheckedRadioButtonId();

        int typePos = mTypesSpinner.getSelectedItemPosition();

        int currencyPos = mCurrencySpinner.getSelectedItemPosition();

        double total = amount;

        if (materialId == R.id.radio_button_leather) {
            if (pendantId == R.id.radio_button_hammer) {
                switch (typePos) {
                    case 0:
                    case 1:
                        total *= 100;
                        break;
                    case 2:
                        total *= 80;
                        break;
                    case 3:
                        total *= 70;
                        break;
                    default:
                        break;
                }
            } else if (pendantId == R.id.radio_button_anchor) {
                switch (typePos) {
                    case 0:
                    case 1:
                        total *= 120;
                        break;
                    case 2:
                        total *= 100;
                        break;
                    case 3:
                        total *= 90;
                        break;
                    default:
                        break;
                }
            }
        } else if (materialId == R.id.radio_button_rope) {
            if (pendantId == R.id.radio_button_hammer) {
                switch (typePos) {
                    case 0:
                    case 1:
                        total *= 90;
                        break;
                    case 2:
                        total *= 70;
                        break;
                    case 3:
                        total *= 50;
                        break;
                    default:
                        break;
                }
            } else if (pendantId == R.id.radio_button_anchor) {
                switch (typePos) {
                    case 0:
                    case 1:
                        total *= 110;
                        break;
                    case 2:
                        total *= 90;
                        break;
                    case 3:
                        total *= 80;
                        break;
                    default:
                        break;
                }
            }
        }

        switch (currencyPos) {
            case 0:
                //total *= 1;
                break;
            case 1:
                total *= 3200;
                break;
            default:
                break;
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String value = df.format(total);
        String resultText = getResources().getString(R.string.message_result);
        DialogFragment newFragment = AlertDialogFragment.newInstance(resultText + " $" + value);
        newFragment.show(getSupportFragmentManager(), resultText);
    }

    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance(String title) {
            AlertDialogFragment frag = new AlertDialogFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String title = getArguments().getString("title");

            return new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setPositiveButton(R.string.alert_dialog_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            }
                    )
                    .create();
        }
    }

}
