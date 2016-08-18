package com.udacity.firebase.shoppinglistplusplus.ui.meals;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.Chat;

/**
 * Created by SteveAndrewWong on 8/5/16.
 */
public class ChatDetailsAdapter extends FirebaseListAdapter<Chat> {

    public ChatDetailsAdapter(Activity activity, Class<Chat> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
    }

    @Override
    protected void populateView(View v, Chat model) {
        super.populateView(v, model);
        String ownerName= model.getOwnerName();
        String chatMessage = model.getChat();

        TextView textViewOwnerName = (TextView) v.findViewById(R.id.text_view_single_chat_created_by);
        TextView textViewChatMessage = (TextView) v.findViewById(R.id.text_view_single_chat_text);

        textViewOwnerName.setText("發信者：" + ownerName);
        textViewChatMessage.setText(chatMessage);
    }
}
