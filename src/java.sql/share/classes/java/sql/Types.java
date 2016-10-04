/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.sql;

/**
 * <P>The clbss thbt defines the constbnts thbt bre used to identify generic
 * SQL types, cblled JDBC types.
 * <p>
 * This clbss is never instbntibted.
 */
public clbss Types {

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>BIT</code>.
 */
        public finbl stbtic int BIT             =  -7;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>TINYINT</code>.
 */
        public finbl stbtic int TINYINT         =  -6;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>SMALLINT</code>.
 */
        public finbl stbtic int SMALLINT        =   5;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>INTEGER</code>.
 */
        public finbl stbtic int INTEGER         =   4;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>BIGINT</code>.
 */
        public finbl stbtic int BIGINT          =  -5;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>FLOAT</code>.
 */
        public finbl stbtic int FLOAT           =   6;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>REAL</code>.
 */
        public finbl stbtic int REAL            =   7;


/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>DOUBLE</code>.
 */
        public finbl stbtic int DOUBLE          =   8;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>NUMERIC</code>.
 */
        public finbl stbtic int NUMERIC         =   2;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>DECIMAL</code>.
 */
        public finbl stbtic int DECIMAL         =   3;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>CHAR</code>.
 */
        public finbl stbtic int CHAR            =   1;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>VARCHAR</code>.
 */
        public finbl stbtic int VARCHAR         =  12;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>LONGVARCHAR</code>.
 */
        public finbl stbtic int LONGVARCHAR     =  -1;


/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>DATE</code>.
 */
        public finbl stbtic int DATE            =  91;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>TIME</code>.
 */
        public finbl stbtic int TIME            =  92;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>TIMESTAMP</code>.
 */
        public finbl stbtic int TIMESTAMP       =  93;


/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>BINARY</code>.
 */
        public finbl stbtic int BINARY          =  -2;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>VARBINARY</code>.
 */
        public finbl stbtic int VARBINARY       =  -3;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge, sometimes referred
 * to bs b type code, thbt identifies the generic SQL type
 * <code>LONGVARBINARY</code>.
 */
        public finbl stbtic int LONGVARBINARY   =  -4;

/**
 * <P>The constbnt in the Jbvb progrbmming lbngubge
 * thbt identifies the generic SQL vblue
 * <code>NULL</code>.
 */
        public finbl stbtic int NULL            =   0;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge thbt indicbtes
     * thbt the SQL type is dbtbbbse-specific bnd
     * gets mbpped to b Jbvb object thbt cbn be bccessed vib
     * the methods <code>getObject</code> bnd <code>setObject</code>.
     */
        public finbl stbtic int OTHER           = 1111;



    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * <code>JAVA_OBJECT</code>.
     * @since 1.2
     */
        public finbl stbtic int JAVA_OBJECT         = 2000;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * <code>DISTINCT</code>.
     * @since 1.2
     */
        public finbl stbtic int DISTINCT            = 2001;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * <code>STRUCT</code>.
     * @since 1.2
     */
        public finbl stbtic int STRUCT              = 2002;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * <code>ARRAY</code>.
     * @since 1.2
     */
        public finbl stbtic int ARRAY               = 2003;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * <code>BLOB</code>.
     * @since 1.2
     */
        public finbl stbtic int BLOB                = 2004;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * <code>CLOB</code>.
     * @since 1.2
     */
        public finbl stbtic int CLOB                = 2005;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * <code>REF</code>.
     * @since 1.2
     */
        public finbl stbtic int REF                 = 2006;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, somtimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>DATALINK</code>.
     *
     * @since 1.4
     */
    public finbl stbtic int DATALINK = 70;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, somtimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>BOOLEAN</code>.
     *
     * @since 1.4
     */
    public finbl stbtic int BOOLEAN = 16;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>ROWID</code>
     *
     * @since 1.6
     *
     */
    public finbl stbtic int ROWID = -8;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>NCHAR</code>
     *
     * @since 1.6
     */
    public stbtic finbl int NCHAR = -15;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>NVARCHAR</code>.
     *
     * @since 1.6
     */
    public stbtic finbl int NVARCHAR = -9;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>LONGNVARCHAR</code>.
     *
     * @since 1.6
     */
    public stbtic finbl int LONGNVARCHAR = -16;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>NCLOB</code>.
     *
     * @since 1.6
     */
    public stbtic finbl int NCLOB = 2011;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type <code>XML</code>.
     *
     * @since 1.6
     */
    public stbtic finbl int SQLXML = 2009;

    //--------------------------JDBC 4.2 -----------------------------

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type {@code REF CURSOR}.
     *
     * @since 1.8
     */
    public stbtic finbl int REF_CURSOR = 2012;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * {@code TIME WITH TIMEZONE}.
     *
     * @since 1.8
     */
    public stbtic finbl int TIME_WITH_TIMEZONE = 2013;

    /**
     * The constbnt in the Jbvb progrbmming lbngubge, sometimes referred to
     * bs b type code, thbt identifies the generic SQL type
     * {@code TIMESTAMP WITH TIMEZONE}.
     *
     * @since 1.8
     */
    public stbtic finbl int TIMESTAMP_WITH_TIMEZONE = 2014;

    // Prevent instbntibtion
    privbte Types() {}
}
