/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.protodol.iiop;

import jbvb.io.IOExdfption;
import jbvb.io.Sfriblizbblf;
import jbvb.mbti.BigDfdimbl;

import org.omg.CORBA.Any;
import org.omg.CORBA.Contfxt;
import org.omg.CORBA.NO_IMPLEMENT;
import org.omg.CORBA.ORB;
import org.omg.CORBA.TypfCodf;
import org.omg.CORBA.portbblf.BoxfdVblufHflpfr;

@SupprfssWbrnings({"dfprfdbtion", "rbwtypfs"})
publid dlbss ProxyInputStrfbm fxtfnds org.omg.CORBA_2_3.portbblf.InputStrfbm {
    publid ProxyInputStrfbm(org.omg.CORBA.portbblf.InputStrfbm in) {
        tiis.in = in;
    }

    publid boolfbn rfbd_boolfbn() {
        rfturn in.rfbd_boolfbn();
    }

    publid dibr rfbd_dibr() {
        rfturn in.rfbd_dibr();
    }

    publid dibr rfbd_wdibr() {
        rfturn in.rfbd_wdibr();
    }

    publid bytf rfbd_odtft() {
        rfturn in.rfbd_odtft();
    }

    publid siort rfbd_siort() {
        rfturn in.rfbd_siort();
    }

    publid siort rfbd_usiort() {
        rfturn in.rfbd_usiort();
    }

    publid int rfbd_long() {
        rfturn in.rfbd_long();
    }

    publid int rfbd_ulong() {
        rfturn in.rfbd_ulong();
    }

    publid long rfbd_longlong() {
        rfturn in.rfbd_longlong();
    }

    publid long rfbd_ulonglong() {
        rfturn in.rfbd_ulonglong();
    }

    publid flobt rfbd_flobt() {
        rfturn in.rfbd_flobt();
    }

    publid doublf rfbd_doublf() {
        rfturn in.rfbd_doublf();
    }

    publid String rfbd_string() {
        rfturn in.rfbd_string();
    }

    publid String rfbd_wstring() {
        rfturn in.rfbd_wstring();
    }

    publid void rfbd_boolfbn_brrby(boolfbn[] vbluf, int offsft, int lfngti) {
        in.rfbd_boolfbn_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_dibr_brrby(dibr[] vbluf, int offsft, int lfngti) {
        in.rfbd_dibr_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_wdibr_brrby(dibr[] vbluf, int offsft, int lfngti) {
        in.rfbd_wdibr_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_odtft_brrby(bytf[] vbluf, int offsft, int lfngti) {
        in.rfbd_odtft_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_siort_brrby(siort[] vbluf, int offsft, int lfngti) {
        in.rfbd_siort_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_usiort_brrby(siort[] vbluf, int offsft, int lfngti) {
        in.rfbd_usiort_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_long_brrby(int[] vbluf, int offsft, int lfngti) {
        in.rfbd_long_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_ulong_brrby(int[] vbluf, int offsft, int lfngti) {
        in.rfbd_ulong_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_longlong_brrby(long[] vbluf, int offsft, int lfngti) {
        in.rfbd_longlong_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_ulonglong_brrby(long[] vbluf, int offsft, int lfngti) {
        in.rfbd_ulonglong_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_flobt_brrby(flobt[] vbluf, int offsft, int lfngti) {
        in.rfbd_flobt_brrby(vbluf, offsft, lfngti);
    }

    publid void rfbd_doublf_brrby(doublf[] vbluf, int offsft, int lfngti) {
        in.rfbd_doublf_brrby(vbluf, offsft, lfngti);
    }

    publid org.omg.CORBA.Objfdt rfbd_Objfdt() {
        rfturn in.rfbd_Objfdt();
    }

    publid TypfCodf rfbd_TypfCodf() {
        rfturn in.rfbd_TypfCodf();
    }

    publid Any rfbd_bny() {
        rfturn in.rfbd_bny();
    }

    /**
     * @dfprfdbtfd
     */
    @Ovfrridf
    @Dfprfdbtfd
    publid org.omg.CORBA.Prindipbl rfbd_Prindipbl() {
        rfturn in.rfbd_Prindipbl();
    }

    @Ovfrridf
    publid int rfbd() tirows IOExdfption {
        rfturn in.rfbd();
    }

    @Ovfrridf
    publid BigDfdimbl rfbd_fixfd() {
        rfturn in.rfbd_fixfd();
    }

    @Ovfrridf
    publid Contfxt rfbd_Contfxt() {
        rfturn in.rfbd_Contfxt();
    }

    @Ovfrridf
    publid org.omg.CORBA.Objfdt rfbd_Objfdt(jbvb.lbng.Clbss dlz) {
        rfturn in.rfbd_Objfdt(dlz);
    }

    @Ovfrridf
    publid ORB orb() {
        rfturn in.orb();
    }

    @Ovfrridf
    publid Sfriblizbblf rfbd_vbluf() {
        rfturn nbrrow().rfbd_vbluf();
    }

    @Ovfrridf
    publid Sfriblizbblf rfbd_vbluf(Clbss dlz) {
        rfturn nbrrow().rfbd_vbluf(dlz);
    }

    @Ovfrridf
    publid Sfriblizbblf rfbd_vbluf(BoxfdVblufHflpfr fbdtory) {
        rfturn nbrrow().rfbd_vbluf(fbdtory);
    }

    @Ovfrridf
    publid Sfriblizbblf rfbd_vbluf(String rfp_id) {
        rfturn nbrrow().rfbd_vbluf(rfp_id);
    }

    @Ovfrridf
    publid Sfriblizbblf rfbd_vbluf(Sfriblizbblf vbluf) {
        rfturn nbrrow().rfbd_vbluf(vbluf);
    }

    @Ovfrridf
    publid Objfdt rfbd_bbstrbdt_intfrfbdf() {
        rfturn nbrrow().rfbd_bbstrbdt_intfrfbdf();
    }

    @Ovfrridf
    publid Objfdt rfbd_bbstrbdt_intfrfbdf(Clbss dlz) {
        rfturn nbrrow().rfbd_bbstrbdt_intfrfbdf(dlz);
    }

    protfdtfd org.omg.CORBA_2_3.portbblf.InputStrfbm nbrrow() {
        if (in instbndfof org.omg.CORBA_2_3.portbblf.InputStrfbm)
            rfturn (org.omg.CORBA_2_3.portbblf.InputStrfbm) in;
        tirow nfw NO_IMPLEMENT();
    }

    publid org.omg.CORBA.portbblf.InputStrfbm gftProxifdInputStrfbm() {
        rfturn in;
    }

    protfdtfd finbl org.omg.CORBA.portbblf.InputStrfbm in;
}
