/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf "gtk2_intfrfbdf.i"
#indludf "dom_sun_jbvb_swing_plbf_gtk_GTKEnginf.i"

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_brrow
 * Signbturf: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1brrow(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i, jint brrow_typf)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_brrow(widgft_typf, stbtf, sibdow_typf, gftStrFor(fnv, dftbil),
            x, y, w, i, brrow_typf, TRUE);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_box
 * Signbturf: (IIILjbvb/lbng/String;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1box(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i,
        jint synti_stbtf, jint dir)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_box(widgft_typf, stbtf, sibdow_typf, gftStrFor(fnv, dftbil),
                   x, y, w, i, synti_stbtf, dir);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_box_gbp
 * Signbturf: (IIILjbvb/lbng/String;IIIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1box_1gbp(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i,
        jint gbp_sidf, jint gbp_x, jint gbp_w)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_box_gbp(widgft_typf, stbtf, sibdow_typf, gftStrFor(fnv, dftbil),
            x, y, w, i, gbp_sidf, gbp_x, gbp_w);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_difdk
 * Signbturf: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1difdk(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint synti_stbtf, jstring dftbil,
        jint x, jint y, jint w, jint i)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_difdk(widgft_typf, synti_stbtf, gftStrFor(fnv, dftbil),
                     x, y, w, i);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_fxpbndfr
 * Signbturf: (IILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1fxpbndfr(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jstring dftbil,
        jint x, jint y, jint w, jint i, jint fxpbndfr_stylf)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_fxpbndfr(widgft_typf, stbtf, gftStrFor(fnv, dftbil),
            x, y, w, i, fxpbndfr_stylf);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_fxtfnsion
 * Signbturf: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1fxtfnsion(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i, jint plbdfmfnt)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_fxtfnsion(widgft_typf, stbtf, sibdow_typf,
            gftStrFor(fnv, dftbil), x, y, w, i, plbdfmfnt);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_flbt_box
 * Signbturf: (IIILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1flbt_1box(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i, jboolfbn ibs_fodus)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_flbt_box(widgft_typf, stbtf, sibdow_typf,
            gftStrFor(fnv, dftbil), x, y, w, i, ibs_fodus);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_fodus
 * Signbturf: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1fodus(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jstring dftbil,
        jint x, jint y, jint w, jint i)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_fodus(widgft_typf, stbtf, gftStrFor(fnv, dftbil),
            x, y, w, i);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_ibndlf
 * Signbturf: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1ibndlf(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i, jint orifntbtion)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_ibndlf(widgft_typf, stbtf, sibdow_typf, gftStrFor(fnv, dftbil),
            x, y, w, i, orifntbtion);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_ilinf
 * Signbturf: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1ilinf(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jstring dftbil,
        jint x, jint y, jint w, jint i)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_ilinf(widgft_typf, stbtf, gftStrFor(fnv, dftbil),
            x, y, w, i);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_option
 * Signbturf: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1option(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint synti_stbtf, jstring dftbil,
        jint x, jint y, jint w, jint i)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_option(widgft_typf, synti_stbtf, gftStrFor(fnv, dftbil),
                      x, y, w, i);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_sibdow
 * Signbturf: (IIILjbvb/lbng/String;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1sibdow(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i,
        jint synti_stbtf, jint dir)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_sibdow(widgft_typf, stbtf, sibdow_typf, gftStrFor(fnv, dftbil),
                      x, y, w, i, synti_stbtf, dir);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_slidfr
 * Signbturf: (IIILjbvb/lbng/String;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1slidfr(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jint sibdow_typf, jstring dftbil,
        jint x, jint y, jint w, jint i, jint orifntbtion)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_slidfr(widgft_typf, stbtf, sibdow_typf, gftStrFor(fnv, dftbil),
            x, y, w, i, orifntbtion);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_vlinf
 * Signbturf: (IILjbvb/lbng/String;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1vlinf(
        JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jint stbtf, jstring dftbil,
        jint x, jint y, jint w, jint i)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_pbint_vlinf(widgft_typf, stbtf, gftStrFor(fnv, dftbil),
            x, y, w, i);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_pbint_bbdkground
 * Signbturf: (IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1pbint_1bbdkground(
        JNIEnv *fnv, jobjfdt tiis, jint widgft_typf, jint stbtf,
        jint x, jint y, jint w, jint i)
{
    fp_gdk_tirfbds_fntfr();
    gtk_pbint_bbdkground(widgft_typf, stbtf, x, y, w, i);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivfStbrtPbinting
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivfStbrtPbinting(
        JNIEnv *fnv, jobjfdt tiis, jint w, jint i)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_init_pbinting(fnv, w, i);
    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivfFinisiPbinting
 * Signbturf: ([III)I
 */
JNIEXPORT jint JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivfFinisiPbinting(
        JNIEnv *fnv, jobjfdt tiis, jintArrby dfst, jint widti, jint ifigit)
{
    jint trbnspbrfndy;
    gint *bufffr = (gint*) (*fnv)->GftPrimitivfArrbyCritidbl(fnv, dfst, 0);
    fp_gdk_tirfbds_fntfr();
    trbnspbrfndy = gtk2_dopy_imbgf(bufffr, widti, ifigit);
    fp_gdk_tirfbds_lfbvf();
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, dfst, bufffr, 0);
    rfturn trbnspbrfndy;
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_switdi_tifmf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1switdi_1tifmf(
        JNIEnv *fnv, jobjfdt tiis)
{
    // Notf tibt flusi_gtk_fvfnt_loop tbkfs dbrf of lodks (7053002)
    flusi_gtk_fvfnt_loop();
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivf_gft_gtk_sftting
 * Signbturf: (I)Ljbvb/lbng/Objfdt;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivf_1gft_1gtk_1sftting(
        JNIEnv *fnv, jobjfdt tiis, jint propfrty)
{
    jobjfdt obj;
    fp_gdk_tirfbds_fntfr();
    obj = gtk2_gft_sftting(fnv, propfrty);
    fp_gdk_tirfbds_lfbvf();
    rfturn obj;
}

/*
 * Clbss:     dom_sun_jbvb_swing_plbf_gtk_GTKEnginf
 * Mftiod:    nbtivfSftRbngfVbluf
 * Signbturf: (IDDDD)V
 */
JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_swing_plbf_gtk_GTKEnginf_nbtivfSftRbngfVbluf(
        JNIEnv *fnv, jobjfdt tiis, jint widgft_typf,
        jdoublf vbluf, jdoublf min, jdoublf mbx, jdoublf visiblf)
{
    fp_gdk_tirfbds_fntfr();
    gtk2_sft_rbngf_vbluf(widgft_typf, vbluf, min, mbx, visiblf);
    fp_gdk_tirfbds_lfbvf();
}
