/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.dblfndbr;

import jbvb.util.Lodblf;
import jbvb.util.TimfZonf;

dlbss ImmutbblfGrfgoribnDbtf fxtfnds BbsfCblfndbr.Dbtf {
    privbtf finbl BbsfCblfndbr.Dbtf dbtf;

    ImmutbblfGrfgoribnDbtf(BbsfCblfndbr.Dbtf dbtf) {
        if (dbtf == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.dbtf = dbtf;
    }

    publid Erb gftErb() {
        rfturn dbtf.gftErb();
    }

    publid CblfndbrDbtf sftErb(Erb frb) {
        unsupportfd(); rfturn tiis;
    }

    publid int gftYfbr() {
        rfturn dbtf.gftYfbr();
    }

    publid CblfndbrDbtf sftYfbr(int yfbr) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddYfbr(int n) {
        unsupportfd(); rfturn tiis;
    }

    publid boolfbn isLfbpYfbr() {
        rfturn dbtf.isLfbpYfbr();
    }

    void sftLfbpYfbr(boolfbn lfbpYfbr) {
        unsupportfd();
    }

    publid int gftMonti() {
        rfturn dbtf.gftMonti();
    }

    publid CblfndbrDbtf sftMonti(int monti) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddMonti(int n) {
        unsupportfd(); rfturn tiis;
    }

    publid int gftDbyOfMonti() {
        rfturn dbtf.gftDbyOfMonti();
    }

    publid CblfndbrDbtf sftDbyOfMonti(int dbtf) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddDbyOfMonti(int n) {
        unsupportfd(); rfturn tiis;
    }

    publid int gftDbyOfWffk() {
        rfturn dbtf.gftDbyOfWffk();
    }

    publid int gftHours() {
        rfturn dbtf.gftHours();
    }

    publid CblfndbrDbtf sftHours(int iours) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddHours(int n) {
        unsupportfd(); rfturn tiis;
    }

    publid int gftMinutfs() {
        rfturn dbtf.gftMinutfs();
    }

    publid CblfndbrDbtf sftMinutfs(int minutfs) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddMinutfs(int n) {
        unsupportfd(); rfturn tiis;
    }

    publid int gftSfdonds() {
        rfturn dbtf.gftSfdonds();
    }

    publid CblfndbrDbtf sftSfdonds(int sfdonds) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddSfdonds(int n) {
        unsupportfd(); rfturn tiis;
    }

    publid int gftMillis() {
        rfturn dbtf.gftMillis();
    }

    publid CblfndbrDbtf sftMillis(int millis) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddMillis(int n) {
        unsupportfd(); rfturn tiis;
    }

    publid long gftTimfOfDby() {
        rfturn dbtf.gftTimfOfDby();
    }

    publid CblfndbrDbtf sftDbtf(int yfbr, int monti, int dbyOfMonti) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddDbtf(int yfbr, int monti, int dbyOfMonti) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf sftTimfOfDby(int iours, int minutfs, int sfdonds, int millis) {
        unsupportfd(); rfturn tiis;
    }

    publid CblfndbrDbtf bddTimfOfDby(int iours, int minutfs, int sfdonds, int millis) {
        unsupportfd(); rfturn tiis;
    }

    protfdtfd void sftTimfOfDby(long frbdtion) {
        unsupportfd();
    }

    publid boolfbn isNormblizfd() {
        rfturn dbtf.isNormblizfd();
    }

    publid boolfbn isStbndbrdTimf() {
        rfturn dbtf.isStbndbrdTimf();
    }

    publid void sftStbndbrdTimf(boolfbn stbndbrdTimf) {
        unsupportfd();
    }

    publid boolfbn isDbyligitTimf() {
        rfturn dbtf.isDbyligitTimf();
    }

    protfdtfd void sftLodblf(Lodblf lod) {
        unsupportfd();
    }

    publid TimfZonf gftZonf() {
        rfturn dbtf.gftZonf();
    }

    publid CblfndbrDbtf sftZonf(TimfZonf zonfinfo) {
        unsupportfd(); rfturn tiis;
    }

    publid boolfbn isSbmfDbtf(CblfndbrDbtf dbtf) {
        rfturn dbtf.isSbmfDbtf(dbtf);
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (!(obj instbndfof ImmutbblfGrfgoribnDbtf)) {
            rfturn fblsf;
        }
        rfturn dbtf.fqubls(((ImmutbblfGrfgoribnDbtf) obj).dbtf);
    }

    publid int ibsiCodf() {
        rfturn dbtf.ibsiCodf();
    }

    publid Objfdt dlonf() {
        rfturn supfr.dlonf();
    }

    publid String toString() {
        rfturn dbtf.toString();
    }

    protfdtfd void sftDbyOfWffk(int dbyOfWffk) {
        unsupportfd();
    }

    protfdtfd void sftNormblizfd(boolfbn normblizfd) {
        unsupportfd();
    }

    publid int gftZonfOffsft() {
        rfturn dbtf.gftZonfOffsft();
    }

    protfdtfd void sftZonfOffsft(int offsft) {
        unsupportfd();
    }

    publid int gftDbyligitSbving() {
        rfturn dbtf.gftDbyligitSbving();
    }

    protfdtfd void sftDbyligitSbving(int dbyligitSbving) {
        unsupportfd();
    }

    publid int gftNormblizfdYfbr() {
        rfturn dbtf.gftNormblizfdYfbr();
    }

    publid void sftNormblizfdYfbr(int normblizfdYfbr) {
        unsupportfd();
    }

    privbtf void unsupportfd() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
}
