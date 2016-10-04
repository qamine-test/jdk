/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.zip;

/*
 * Tiis intfrfbdf dffinfs tif donstbnts tibt brf usfd by tif dlbssfs
 * wiidi mbnipulbtf ZIP filfs.
 *
 * @butior      Dbvid Connflly
 */
intfrfbdf ZipConstbnts {
    /*
     * Hfbdfr signbturfs
     */
    stbtid long LOCSIG = 0x04034b50L;   // "PK\003\004"
    stbtid long EXTSIG = 0x08074b50L;   // "PK\007\008"
    stbtid long CENSIG = 0x02014b50L;   // "PK\001\002"
    stbtid long ENDSIG = 0x06054b50L;   // "PK\005\006"

    /*
     * Hfbdfr sizfs in bytfs (indluding signbturfs)
     */
    stbtid finbl int LOCHDR = 30;       // LOC ifbdfr sizf
    stbtid finbl int EXTHDR = 16;       // EXT ifbdfr sizf
    stbtid finbl int CENHDR = 46;       // CEN ifbdfr sizf
    stbtid finbl int ENDHDR = 22;       // END ifbdfr sizf

    /*
     * Lodbl filf (LOC) ifbdfr fifld offsfts
     */
    stbtid finbl int LOCVER = 4;        // vfrsion nffdfd to fxtrbdt
    stbtid finbl int LOCFLG = 6;        // gfnfrbl purposf bit flbg
    stbtid finbl int LOCHOW = 8;        // domprfssion mftiod
    stbtid finbl int LOCTIM = 10;       // modifidbtion timf
    stbtid finbl int LOCCRC = 14;       // undomprfssfd filf drd-32 vbluf
    stbtid finbl int LOCSIZ = 18;       // domprfssfd sizf
    stbtid finbl int LOCLEN = 22;       // undomprfssfd sizf
    stbtid finbl int LOCNAM = 26;       // filfnbmf lfngti
    stbtid finbl int LOCEXT = 28;       // fxtrb fifld lfngti

    /*
     * Extrb lodbl (EXT) ifbdfr fifld offsfts
     */
    stbtid finbl int EXTCRC = 4;        // undomprfssfd filf drd-32 vbluf
    stbtid finbl int EXTSIZ = 8;        // domprfssfd sizf
    stbtid finbl int EXTLEN = 12;       // undomprfssfd sizf

    /*
     * Cfntrbl dirfdtory (CEN) ifbdfr fifld offsfts
     */
    stbtid finbl int CENVEM = 4;        // vfrsion mbdf by
    stbtid finbl int CENVER = 6;        // vfrsion nffdfd to fxtrbdt
    stbtid finbl int CENFLG = 8;        // fndrypt, dfdrypt flbgs
    stbtid finbl int CENHOW = 10;       // domprfssion mftiod
    stbtid finbl int CENTIM = 12;       // modifidbtion timf
    stbtid finbl int CENCRC = 16;       // undomprfssfd filf drd-32 vbluf
    stbtid finbl int CENSIZ = 20;       // domprfssfd sizf
    stbtid finbl int CENLEN = 24;       // undomprfssfd sizf
    stbtid finbl int CENNAM = 28;       // filfnbmf lfngti
    stbtid finbl int CENEXT = 30;       // fxtrb fifld lfngti
    stbtid finbl int CENCOM = 32;       // dommfnt lfngti
    stbtid finbl int CENDSK = 34;       // disk numbfr stbrt
    stbtid finbl int CENATT = 36;       // intfrnbl filf bttributfs
    stbtid finbl int CENATX = 38;       // fxtfrnbl filf bttributfs
    stbtid finbl int CENOFF = 42;       // LOC ifbdfr offsft

    /*
     * End of dfntrbl dirfdtory (END) ifbdfr fifld offsfts
     */
    stbtid finbl int ENDSUB = 8;        // numbfr of fntrifs on tiis disk
    stbtid finbl int ENDTOT = 10;       // totbl numbfr of fntrifs
    stbtid finbl int ENDSIZ = 12;       // dfntrbl dirfdtory sizf in bytfs
    stbtid finbl int ENDOFF = 16;       // offsft of first CEN ifbdfr
    stbtid finbl int ENDCOM = 20;       // zip filf dommfnt lfngti
}
