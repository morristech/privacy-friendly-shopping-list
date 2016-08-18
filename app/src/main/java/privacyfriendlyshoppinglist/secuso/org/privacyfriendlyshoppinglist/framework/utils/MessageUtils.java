package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.Toast;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import rx.Observable;

/**
 * Description:
 * Author: Grebiel Jose Ifill Brito
 * Created: 18.08.16 creation date
 */
public class MessageUtils
{
    private static int NOTHING = -1;

    public static void showToast(Context context, int messageStringResource, int toastLength)
    {
        Toast toast = Toast.makeText(context, messageStringResource, toastLength);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showAlertDialog(Context context, int titleResource, int messageResource, Observable action)
    {
        showAlertDialog(context, titleResource, messageResource, null, action);
    }

    public static void showAlertDialog(Context context, int titleResource, int messageResource, String customText, Observable action)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        if ( titleResource != NOTHING )
        {
            String title = context.getResources().getString(titleResource);
            dialogBuilder.setTitle(title);
        }
        if ( messageResource != NOTHING )
        {
            String message;
            if ( customText == null )
            {
                message = context.getResources().getString(messageResource);
            }
            else
            {
                message = context.getResources().getString(messageResource, customText);
            }
            dialogBuilder.setMessage(message);

        }
        dialogBuilder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                action.subscribe();
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                // do nothing
            }
        });
        dialogBuilder.setIcon(R.drawable.ic_dialog_alert_yellow);
        dialogBuilder.show();
    }
}