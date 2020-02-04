package dev.jdvila.josevilamailchimpapp.views.recyclerviews.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;

import dev.jdvila.josevilamailchimpapp.R;
import dev.jdvila.josevilamailchimpapp.model.Member;
import dev.jdvila.josevilamailchimpapp.views.ListItem;
import dev.jdvila.josevilamailchimpapp.views.MemberListItem;
import dev.jdvila.josevilamailchimpapp.views.recyclerviews.adapter.MembersListRecyclerViewAdapter;

public class MembersListRecyclerViewMemberViewHolder extends MembersListRecyclerViewViewHolder {
    private static final int ICON_SIZE = 150;
    private static final float ICON_CIRCULAR_IMAGE_RADIUS_DIVISOR = 2.0f;
    private ImageView profileImageView;
    private TextView fullNameTextView;
    private TextView emailTextView;

    public MembersListRecyclerViewMemberViewHolder(@NonNull View itemView) {
        super(itemView);
        profileImageView = itemView.findViewById(R.id.profileImageView);
        fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
        emailTextView = itemView.findViewById(R.id.emailTextView);
    }

    @Override
    public void onBind(@NotNull ListItem listItem, @NotNull final MembersListRecyclerViewAdapter.OnMemberItemViewListener onMemberItemViewListener) {
        final Member member = ((MemberListItem) listItem).getMember();
        String imageUrl = null != member.getMergeFields().getPhoto() ? member.getMergeFields().getPhoto() : itemView.getContext().getResources().getString(R.string.null_photo_url_placeholder);

        Picasso.get().load(imageUrl)
                .resize(ICON_SIZE, ICON_SIZE)
                .into(profileImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) profileImageView.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(itemView.getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / ICON_CIRCULAR_IMAGE_RADIUS_DIVISOR);
                        profileImageView.setImageDrawable(imageDrawable);
                    }

                    @Override
                    public void onError(Exception e) {
                        try {
                            throw new MalformedURLException(itemView.getContext().getResources().getString(R.string.invalid_image_url_error));
                        } catch (MalformedURLException ex) {
                            ex.printStackTrace();
                        }
                    }

                });

        String fullName = (member.getMergeFields().getfName() + " " + member.getMergeFields().getlName()).trim();
        String email = member.getEmailAddress();
        fullNameTextView.setText(fullName);
        emailTextView.setText(email);

        profileImageView.setTransitionName(member.getId());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMemberItemViewListener.OnMemberItemViewClick(member, profileImageView);
            }
        });
    }
}
