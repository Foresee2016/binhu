package org.foresee.binhu.data;

public class DbSchama {
    public static final class UpdateInfoTable{
        public static final String TABLE_NAME="update_info";
        public static final class Cols{
            public static final String PART="part";
            public static final String DATASIZE="dataSize";
            public static final String DATACOUNT="dataCount";
            public static final String UPDATETIME="update_time";
        }
    }
    public static final class MedicineTable{
        public static final String TABLE_NAME="medicine";
        public static final class Cols{
            public static final String ID="id";
            public static final String NAME="name";
            public static final String THUMBNAIL="thumbnail";
            public static final String TASTE="taste";
            public static final String FUNC="func";
            public static final String IMAGES="images";
            public static final String UPDATETIME="update_time";
        }
    }
    public static final class PrescriptTable{
        public static final String TABLE_NAME="prescript";
        public static final class Cols{
            public static final String ID="id";
            public static final String NAME="name";
            public static final String COMPONENTS="components";
            public static final String TASTE="taste";
            public static final String FUNC="func";
            public static final String UPDATETIME="update_time";
        }
    }
    public static final class ComponentTable{
        public static final String TABLE_NAME="component";
        public static final class Cols{
            public static final String ID="id";
            public static final String PRESCRIPTID="prescript_id";
            public static final String MEDICINENAME="medicine_name";
            public static final String WEIGHT="weight";
            public static final String PROCESS="process";
        }
    }
}
