/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvb.sql;

/**
 * <P>Tif dlbss tibt dffinfs tif donstbnts tibt brf usfd to idfntify gfnfrid
 * SQL typfs, dbllfd JDBC typfs.
 * <p>
 * Tiis dlbss is nfvfr instbntibtfd.
 */
publid dlbss Typfs {

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>BIT</dodf>.
 */
        publid finbl stbtid int BIT             =  -7;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>TINYINT</dodf>.
 */
        publid finbl stbtid int TINYINT         =  -6;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>SMALLINT</dodf>.
 */
        publid finbl stbtid int SMALLINT        =   5;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>INTEGER</dodf>.
 */
        publid finbl stbtid int INTEGER         =   4;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>BIGINT</dodf>.
 */
        publid finbl stbtid int BIGINT          =  -5;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>FLOAT</dodf>.
 */
        publid finbl stbtid int FLOAT           =   6;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>REAL</dodf>.
 */
        publid finbl stbtid int REAL            =   7;


/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>DOUBLE</dodf>.
 */
        publid finbl stbtid int DOUBLE          =   8;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>NUMERIC</dodf>.
 */
        publid finbl stbtid int NUMERIC         =   2;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>DECIMAL</dodf>.
 */
        publid finbl stbtid int DECIMAL         =   3;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>CHAR</dodf>.
 */
        publid finbl stbtid int CHAR            =   1;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>VARCHAR</dodf>.
 */
        publid finbl stbtid int VARCHAR         =  12;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>LONGVARCHAR</dodf>.
 */
        publid finbl stbtid int LONGVARCHAR     =  -1;


/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>DATE</dodf>.
 */
        publid finbl stbtid int DATE            =  91;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>TIME</dodf>.
 */
        publid finbl stbtid int TIME            =  92;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>TIMESTAMP</dodf>.
 */
        publid finbl stbtid int TIMESTAMP       =  93;


/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>BINARY</dodf>.
 */
        publid finbl stbtid int BINARY          =  -2;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>VARBINARY</dodf>.
 */
        publid finbl stbtid int VARBINARY       =  -3;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd
 * to bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
 * <dodf>LONGVARBINARY</dodf>.
 */
        publid finbl stbtid int LONGVARBINARY   =  -4;

/**
 * <P>Tif donstbnt in tif Jbvb progrbmming lbngubgf
 * tibt idfntififs tif gfnfrid SQL vbluf
 * <dodf>NULL</dodf>.
 */
        publid finbl stbtid int NULL            =   0;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf tibt indidbtfs
     * tibt tif SQL typf is dbtbbbsf-spfdifid bnd
     * gfts mbppfd to b Jbvb objfdt tibt dbn bf bddfssfd vib
     * tif mftiods <dodf>gftObjfdt</dodf> bnd <dodf>sftObjfdt</dodf>.
     */
        publid finbl stbtid int OTHER           = 1111;



    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * <dodf>JAVA_OBJECT</dodf>.
     * @sindf 1.2
     */
        publid finbl stbtid int JAVA_OBJECT         = 2000;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * <dodf>DISTINCT</dodf>.
     * @sindf 1.2
     */
        publid finbl stbtid int DISTINCT            = 2001;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * <dodf>STRUCT</dodf>.
     * @sindf 1.2
     */
        publid finbl stbtid int STRUCT              = 2002;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * <dodf>ARRAY</dodf>.
     * @sindf 1.2
     */
        publid finbl stbtid int ARRAY               = 2003;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * <dodf>BLOB</dodf>.
     * @sindf 1.2
     */
        publid finbl stbtid int BLOB                = 2004;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * <dodf>CLOB</dodf>.
     * @sindf 1.2
     */
        publid finbl stbtid int CLOB                = 2005;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * <dodf>REF</dodf>.
     * @sindf 1.2
     */
        publid finbl stbtid int REF                 = 2006;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somtimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>DATALINK</dodf>.
     *
     * @sindf 1.4
     */
    publid finbl stbtid int DATALINK = 70;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somtimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>BOOLEAN</dodf>.
     *
     * @sindf 1.4
     */
    publid finbl stbtid int BOOLEAN = 16;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>ROWID</dodf>
     *
     * @sindf 1.6
     *
     */
    publid finbl stbtid int ROWID = -8;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>NCHAR</dodf>
     *
     * @sindf 1.6
     */
    publid stbtid finbl int NCHAR = -15;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>NVARCHAR</dodf>.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int NVARCHAR = -9;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>LONGNVARCHAR</dodf>.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int LONGNVARCHAR = -16;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>NCLOB</dodf>.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int NCLOB = 2011;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf <dodf>XML</dodf>.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int SQLXML = 2009;

    //--------------------------JDBC 4.2 -----------------------------

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf {@dodf REF CURSOR}.
     *
     * @sindf 1.8
     */
    publid stbtid finbl int REF_CURSOR = 2012;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * {@dodf TIME WITH TIMEZONE}.
     *
     * @sindf 1.8
     */
    publid stbtid finbl int TIME_WITH_TIMEZONE = 2013;

    /**
     * Tif donstbnt in tif Jbvb progrbmming lbngubgf, somftimfs rfffrrfd to
     * bs b typf dodf, tibt idfntififs tif gfnfrid SQL typf
     * {@dodf TIMESTAMP WITH TIMEZONE}.
     *
     * @sindf 1.8
     */
    publid stbtid finbl int TIMESTAMP_WITH_TIMEZONE = 2014;

    // Prfvfnt instbntibtion
    privbtf Typfs() {}
}
