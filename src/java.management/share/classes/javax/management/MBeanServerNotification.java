/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * Rfprfsfnts b notifidbtion fmittfd by tif MBfbn Sfrvfr tirougi tif MBfbnSfrvfrDflfgbtf MBfbn.
 * Tif MBfbn Sfrvfr fmits tif following typfs of notifidbtions: MBfbn rfgistrbtion, MBfbn
 * unrfgistrbtion.
 * <P>
 * To rfdfivf MBfbnSfrvfrNotifidbtions, you nffd to rfgistfr b listfnfr witi
 * tif {@link MBfbnSfrvfrDflfgbtf MBfbnSfrvfrDflfgbtf} MBfbn
 * tibt rfprfsfnts tif MBfbnSfrvfr. Tif ObjfdtNbmf of tif MBfbnSfrvfrDflfgbtf is
 * {@link MBfbnSfrvfrDflfgbtf#DELEGATE_NAME}, wiidi is
 * <CODE>JMImplfmfntbtion:typf=MBfbnSfrvfrDflfgbtf</CODE>.
 *
 * <p>Tif following dodf prints b mfssbgf fvfry timf bn MBfbn is rfgistfrfd
 * or unrfgistfrfd in tif MBfbn Sfrvfr {@dodf mbfbnSfrvfr}:</p>
 *
 * <prf>
 * privbtf stbtid finbl NotifidbtionListfnfr printListfnfr = nfw NotifidbtionListfnfr() {
 *     publid void ibndlfNotifidbtion(Notifidbtion n, Objfdt ibndbbdk) {
 *         if (!(n instbndfof MBfbnSfrvfrNotifidbtion)) {
 *             Systfm.out.println("Ignorfd notifidbtion of dlbss " + n.gftClbss().gftNbmf());
 *             rfturn;
 *         }
 *         MBfbnSfrvfrNotifidbtion mbsn = (MBfbnSfrvfrNotifidbtion) n;
 *         String wibt;
 *         if (n.gftTypf().fqubls(MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION))
 *             wibt = "MBfbn rfgistfrfd";
 *         flsf if (n.gftTypf().fqubls(MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION))
 *             wibt = "MBfbn unrfgistfrfd";
 *         flsf
 *             wibt = "Unknown typf " + n.gftTypf();
 *         Systfm.out.println("Rfdfivfd MBfbn Sfrvfr notifidbtion: " + wibt + ": " +
 *                 mbsn.gftMBfbnNbmf());
 *     }
 * };
 *
 * ...
 *     mbfbnSfrvfr.bddNotifidbtionListfnfr(
 *             MBfbnSfrvfrDflfgbtf.DELEGATE_NAME, printListfnfr, null, null);
 * </prf>
 *
 * <p id="group">
 * An MBfbn wiidi is not bn {@link MBfbnSfrvfrDflfgbtf} mby blso fmit
 * MBfbnSfrvfrNotifidbtions. In pbrtidulbr, tifrf is b donvfntion for
 * MBfbns to fmit bn MBfbnSfrvfrNotifidbtion for b group of MBfbns.</p>
 *
 * <p>An MBfbnSfrvfrNotifidbtion fmittfd to dfnotf tif rfgistrbtion or
 * unrfgistrbtion of b group of MBfbns ibs tif following dibrbdtfristids:
 * <ul><li>Its {@linkplbin Notifidbtion#gftTypf() notifidbtion typf} is
 *     {@dodf "JMX.mbfbn.rfgistfrfd.group"} or
 *     {@dodf "JMX.mbfbn.unrfgistfrfd.group"}, wiidi dbn blso bf writtfn {@link
 *     MBfbnSfrvfrNotifidbtion#REGISTRATION_NOTIFICATION}{@dodf + ".group"} or
 *     {@link
 *     MBfbnSfrvfrNotifidbtion#UNREGISTRATION_NOTIFICATION}{@dodf + ".group"}.
 * </li>
 * <li>Its {@linkplbin #gftMBfbnNbmf() MBfbn nbmf} is bn ObjfdtNbmf pbttfrn
 *     tibt sflfdts tif sft (or b supfrsft) of tif MBfbns bfing rfgistfrfd
 *     or unrfgistfrfd</li>
 * <li>Its {@linkplbin Notifidbtion#gftUsfrDbtb() usfr dbtb} dbn optionblly
 *     bf sft to bn brrby of ObjfdtNbmfs dontbining tif nbmfs of bll MBfbns
 *     bfing rfgistfrfd or unrfgistfrfd.</li>
 * </ul>
 *
 * <p>
 * MBfbns wiidi fmit tifsf group rfgistrbtion/unrfgistrbtion notifidbtions will
 * dfdlbrf tifm in tifir {@link MBfbnInfo#gftNotifidbtions()
 * MBfbnNotifidbtionInfo}.
 * </p>
 *
 * @sindf 1.5
 */
publid dlbss MBfbnSfrvfrNotifidbtion fxtfnds Notifidbtion {


    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 2876477500475969677L;
    /**
     * Notifidbtion typf dfnoting tibt bn MBfbn ibs bffn rfgistfrfd.
     * Vbluf is "JMX.mbfbn.rfgistfrfd".
     */
    publid stbtid finbl String REGISTRATION_NOTIFICATION =
            "JMX.mbfbn.rfgistfrfd";
    /**
     * Notifidbtion typf dfnoting tibt bn MBfbn ibs bffn unrfgistfrfd.
     * Vbluf is "JMX.mbfbn.unrfgistfrfd".
     */
    publid stbtid finbl String UNREGISTRATION_NOTIFICATION =
            "JMX.mbfbn.unrfgistfrfd";
    /**
     * @sfribl Tif objfdt nbmfs of tif MBfbns dondfrnfd by tiis notifidbtion
     */
    privbtf finbl ObjfdtNbmf objfdtNbmf;

    /**
     * Crfbtfs bn MBfbnSfrvfrNotifidbtion objfdt spfdifying objfdt nbmfs of
     * tif MBfbns tibt dbusfd tif notifidbtion bnd tif spfdififd notifidbtion
     * typf.
     *
     * @pbrbm typf A string dfnoting tif typf of tif
     * notifidbtion. Sft it to onf tifsf vblufs: {@link
     * #REGISTRATION_NOTIFICATION}, {@link
     * #UNREGISTRATION_NOTIFICATION}.
     * @pbrbm sourdf Tif MBfbnSfrvfrNotifidbtion objfdt rfsponsiblf
     * for forwbrding MBfbn sfrvfr notifidbtion.
     * @pbrbm sfqufndfNumbfr A sfqufndf numbfr tibt dbn bf usfd to ordfr
     * rfdfivfd notifidbtions.
     * @pbrbm objfdtNbmf Tif objfdt nbmf of tif MBfbn tibt dbusfd tif
     * notifidbtion.
     *
     */
    publid MBfbnSfrvfrNotifidbtion(String typf, Objfdt sourdf,
            long sfqufndfNumbfr, ObjfdtNbmf objfdtNbmf) {
        supfr(typf, sourdf, sfqufndfNumbfr);
        tiis.objfdtNbmf = objfdtNbmf;
    }

    /**
     * Rfturns tif  objfdt nbmf of tif MBfbn tibt dbusfd tif notifidbtion.
     *
     * @rfturn tif objfdt nbmf of tif MBfbn tibt dbusfd tif notifidbtion.
     */
    publid ObjfdtNbmf gftMBfbnNbmf() {
        rfturn objfdtNbmf;
    }

    @Ovfrridf
    publid String toString() {
        rfturn supfr.toString() + "[mbfbnNbmf=" + objfdtNbmf + "]";

    }

 }
