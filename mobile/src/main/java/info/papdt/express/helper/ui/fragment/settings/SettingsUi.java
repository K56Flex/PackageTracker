package info.papdt.express.helper.ui.fragment.settings;

import android.os.Build;
import android.os.Bundle;

import info.papdt.express.helper.R;
import info.papdt.express.helper.support.Settings;
import rikka.materialpreference.ListPreference;
import rikka.materialpreference.Preference;
import rikka.materialpreference.SwitchPreference;

public class SettingsUi extends AbsPrefFragment implements Preference.OnPreferenceChangeListener {

	private SwitchPreference mPrefNavigationTint;
	private ListPreference mPrefNightMode;

	@Override
	public void onCreatePreferences(Bundle bundle, String s) {
		addPreferencesFromResource(R.xml.settings_ui);

		/** findPreference */
		mPrefNavigationTint = (SwitchPreference) findPreference("navigation_tint");
		mPrefNightMode = (ListPreference) findPreference("night_mode");

		/** Default value */
		mPrefNavigationTint.setEnabled(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
		mPrefNavigationTint.setChecked(getSettings().getBoolean(Settings.KEY_NAVIGATION_TINT, true));

		int target = getSettings().getInt(Settings.KEY_NIGHT_MODE, 0);
		if (mPrefNightMode.getValue() == null) {
			mPrefNightMode.setValueIndex(target);
		}

		/** Set callback */
		mPrefNavigationTint.setOnPreferenceChangeListener(this);
		mPrefNightMode.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceChange(Preference pref, Object o) {
		if (pref == mPrefNavigationTint) {
			Boolean b = (Boolean) o;
			getSettings().putBoolean(Settings.KEY_NAVIGATION_TINT, b);
			makeRestartTips();
			return true;
		}
		if (pref == mPrefNightMode) {
			int value = Integer.parseInt((String) o);
			getSettings().putInt(Settings.KEY_NIGHT_MODE, value);
			makeRestartTips();
			return true;
		}
		return false;
	}

}
