package xyz.oribuin.rankupmax.manager;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.oribuin.rankupmax.RankupMax;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager extends Manager {

    public ConfigManager(RankupMax plugin) {
        super(plugin);
        this.reload();
    }

    @Override
    public void reload() {
        // Reload config
        this.plugin.reloadConfig();

        // Save default configuration
        this.plugin.saveDefaultConfig();

        // Define the configuration
        FileConfiguration config = this.plugin.getConfig();

        for (Setting value : Setting.values()) {
            if (config.get(value.key) == null) {
                config.set(value.key, value.defaultValue);
            }

            value.load(config);
        }

        try {
            config.save(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public enum Setting {

        TEST("test", "test");


        private final String key;
        private final Object defaultValue;
        private Object value = null;

        Setting(String key, Object defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }


        /**
         * Gets the setting as a boolean
         *
         * @return The setting as a boolean
         */
        public boolean getBoolean() {
            this.loadValue();

            return (boolean) this.value;
        }

        /**
         * @return the setting as an int
         */
        public int getInt() {
            this.loadValue();

            return (int) this.getNumber();
        }

        /**
         * @return the setting as a long
         */
        public long getLong() {
            this.loadValue();

            return (long) this.getNumber();
        }

        /**
         * @return the setting as a double
         */
        public double getDouble() {
            this.loadValue();

            return this.getNumber();
        }

        /**
         * @return the setting as a float
         */
        public float getFloat() {
            this.loadValue();

            return (float) this.getNumber();
        }

        /**
         * @return the setting as a String
         */
        public String getString() {
            this.loadValue();

            return (String) this.value;
        }

        private double getNumber() {
            this.loadValue();

            if (this.value instanceof Integer) {
                return (int) this.value;
            } else if (this.value instanceof Short) {
                return (short) this.value;
            } else if (this.value instanceof Byte) {
                return (byte) this.value;
            } else if (this.value instanceof Float) {
                return (float) this.value;
            }

            return (double) this.value;
        }

        /**
         * @return the setting as a string list
         */
        @SuppressWarnings("unchecked")
        public List<String> getStringList() {
            this.loadValue();

            return (List<String>) this.value;
        }

        /**
         * Loads the value from the config and caches it
         */
        private void load(FileConfiguration config) {
            this.value = config.get(this.key);
        }

        private void loadValue() {
            if (this.value != null)
                return;

            this.value = RankupMax.getInstance().getConfig().get(this.key);

            if (this.value == null) {
                RankupMax.getInstance().getConfig().set(this.key, this.defaultValue);
            }
        }
    }
}
