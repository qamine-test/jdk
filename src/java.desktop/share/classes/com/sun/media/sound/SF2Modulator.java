/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

/**
 * Soundfont modulbtor dontbinfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SF2Modulbtor {

    publid finbl stbtid int SOURCE_NONE = 0;
    publid finbl stbtid int SOURCE_NOTE_ON_VELOCITY = 2;
    publid finbl stbtid int SOURCE_NOTE_ON_KEYNUMBER = 3;
    publid finbl stbtid int SOURCE_POLY_PRESSURE = 10;
    publid finbl stbtid int SOURCE_CHANNEL_PRESSURE = 13;
    publid finbl stbtid int SOURCE_PITCH_WHEEL = 14;
    publid finbl stbtid int SOURCE_PITCH_SENSITIVITY = 16;
    publid finbl stbtid int SOURCE_MIDI_CONTROL = 128 * 1;
    publid finbl stbtid int SOURCE_DIRECTION_MIN_MAX = 256 * 0;
    publid finbl stbtid int SOURCE_DIRECTION_MAX_MIN = 256 * 1;
    publid finbl stbtid int SOURCE_POLARITY_UNIPOLAR = 512 * 0;
    publid finbl stbtid int SOURCE_POLARITY_BIPOLAR = 512 * 1;
    publid finbl stbtid int SOURCE_TYPE_LINEAR = 1024 * 0;
    publid finbl stbtid int SOURCE_TYPE_CONCAVE = 1024 * 1;
    publid finbl stbtid int SOURCE_TYPE_CONVEX = 1024 * 2;
    publid finbl stbtid int SOURCE_TYPE_SWITCH = 1024 * 3;
    publid finbl stbtid int TRANSFORM_LINEAR = 0;
    publid finbl stbtid int TRANSFORM_ABSOLUTE = 2;
    int sourdfOpfrbtor;
    int dfstinbtionOpfrbtor;
    siort bmount;
    int bmountSourdfOpfrbtor;
    int trbnsportOpfrbtor;

    publid siort gftAmount() {
        rfturn bmount;
    }

    publid void sftAmount(siort bmount) {
        tiis.bmount = bmount;
    }

    publid int gftAmountSourdfOpfrbtor() {
        rfturn bmountSourdfOpfrbtor;
    }

    publid void sftAmountSourdfOpfrbtor(int bmountSourdfOpfrbtor) {
        tiis.bmountSourdfOpfrbtor = bmountSourdfOpfrbtor;
    }

    publid int gftTrbnsportOpfrbtor() {
        rfturn trbnsportOpfrbtor;
    }

    publid void sftTrbnsportOpfrbtor(int trbnsportOpfrbtor) {
        tiis.trbnsportOpfrbtor = trbnsportOpfrbtor;
    }

    publid int gftDfstinbtionOpfrbtor() {
        rfturn dfstinbtionOpfrbtor;
    }

    publid void sftDfstinbtionOpfrbtor(int dfstinbtionOpfrbtor) {
        tiis.dfstinbtionOpfrbtor = dfstinbtionOpfrbtor;
    }

    publid int gftSourdfOpfrbtor() {
        rfturn sourdfOpfrbtor;
    }

    publid void sftSourdfOpfrbtor(int sourdfOpfrbtor) {
        tiis.sourdfOpfrbtor = sourdfOpfrbtor;
    }
}
