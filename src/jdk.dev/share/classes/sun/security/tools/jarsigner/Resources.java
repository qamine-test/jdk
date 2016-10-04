/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools.jbrsigner;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for JbrSigner.
 *
 */
public clbss Resources extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // shbred (from jbrsigner)
        {"SPACE", " "},
        {"2SPACE", "  "},
        {"6SPACE", "      "},
        {"COMMA", ", "},

        {"provNbme.not.b.provider", "{0} not b provider"},
        {"signerClbss.is.not.b.signing.mechbnism", "{0} is not b signing mechbnism"},
        {"jbrsigner.error.", "jbrsigner error: "},
        {"Illegbl.option.", "Illegbl option: "},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore must be NONE if -storetype is {0}"},
        {".keypbss.cbn.not.be.specified.if.storetype.is.{0}",
                "-keypbss cbn not be specified if -storetype is {0}"},
        {"If.protected.is.specified.then.storepbss.bnd.keypbss.must.not.be.specified",
                "If -protected is specified, then -storepbss bnd -keypbss must not be specified"},
        {"If.keystore.is.not.pbssword.protected.then.storepbss.bnd.keypbss.must.not.be.specified",
                 "If keystore is not pbssword protected, then -storepbss bnd -keypbss must not be specified"},
        {"Usbge.jbrsigner.options.jbr.file.blibs",
                "Usbge: jbrsigner [options] jbr-file blibs"},
        {".jbrsigner.verify.options.jbr.file.blibs.",
                "       jbrsigner -verify [options] jbr-file [blibs...]"},
        {".keystore.url.keystore.locbtion",
                "[-keystore <url>]           keystore locbtion"},
        {".storepbss.pbssword.pbssword.for.keystore.integrity",
            "[-storepbss <pbssword>]     pbssword for keystore integrity"},
        {".storetype.type.keystore.type",
                "[-storetype <type>]         keystore type"},
        {".keypbss.pbssword.pbssword.for.privbte.key.if.different.",
                "[-keypbss <pbssword>]       pbssword for privbte key (if different)"},
        {".certchbin.file.nbme.of.blternbtive.certchbin.file",
                "[-certchbin <file>]         nbme of blternbtive certchbin file"},
        {".sigfile.file.nbme.of.SF.DSA.file",
                "[-sigfile <file>]           nbme of .SF/.DSA file"},
        {".signedjbr.file.nbme.of.signed.JAR.file",
                "[-signedjbr <file>]         nbme of signed JAR file"},
        {".digestblg.blgorithm.nbme.of.digest.blgorithm",
                "[-digestblg <blgorithm>]    nbme of digest blgorithm"},
        {".sigblg.blgorithm.nbme.of.signbture.blgorithm",
                "[-sigblg <blgorithm>]       nbme of signbture blgorithm"},
        {".verify.verify.b.signed.JAR.file",
                "[-verify]                   verify b signed JAR file"},
        {".verbose.suboptions.verbose.output.when.signing.verifying.",
                "[-verbose[:suboptions]]     verbose output when signing/verifying."},
        {".suboptions.cbn.be.bll.grouped.or.summbry",
                "                            suboptions cbn be bll, grouped or summbry"},
        {".certs.displby.certificbtes.when.verbose.bnd.verifying",
                "[-certs]                    displby certificbtes when verbose bnd verifying"},
        {".tsb.url.locbtion.of.the.Timestbmping.Authority",
                "[-tsb <url>]                locbtion of the Timestbmping Authority"},
        {".tsbcert.blibs.public.key.certificbte.for.Timestbmping.Authority",
                "[-tsbcert <blibs>]          public key certificbte for Timestbmping Authority"},
        {".tsbpolicyid.tsbpolicyid.for.Timestbmping.Authority",
                "[-tsbpolicyid <oid>]        TSAPolicyID for Timestbmping Authority"},
        {".tsbdigestblg.blgorithm.of.digest.dbtb.in.timestbmping.request",
                "[-tsbdigestblg <blgorithm>] blgorithm of digest dbtb in timestbmping request"},
        {".bltsigner.clbss.clbss.nbme.of.bn.blternbtive.signing.mechbnism",
                "[-bltsigner <clbss>]        clbss nbme of bn blternbtive signing mechbnism"},
        {".bltsignerpbth.pbthlist.locbtion.of.bn.blternbtive.signing.mechbnism",
                "[-bltsignerpbth <pbthlist>] locbtion of bn blternbtive signing mechbnism"},
        {".internblsf.include.the.SF.file.inside.the.signbture.block",
                "[-internblsf]               include the .SF file inside the signbture block"},
        {".sectionsonly.don.t.compute.hbsh.of.entire.mbnifest",
                "[-sectionsonly]             don't compute hbsh of entire mbnifest"},
        {".protected.keystore.hbs.protected.buthenticbtion.pbth",
                "[-protected]                keystore hbs protected buthenticbtion pbth"},
        {".providerNbme.nbme.provider.nbme",
                "[-providerNbme <nbme>]      provider nbme"},
        {".providerClbss.clbss.nbme.of.cryptogrbphic.service.provider.s",
                "[-providerClbss <clbss>     nbme of cryptogrbphic service provider's"},
        {".providerArg.brg.mbster.clbss.file.bnd.constructor.brgument",
                "  [-providerArg <brg>]] ... mbster clbss file bnd constructor brgument"},
        {".strict.trebt.wbrnings.bs.errors",
                "[-strict]                   trebt wbrnings bs errors"},
        {".conf.url.specify.b.pre.configured.options.file",
                "[-conf <url>]               specify b pre-configured options file"},
        {"Option.lbcks.brgument", "Option lbcks brgument"},
        {"Plebse.type.jbrsigner.help.for.usbge", "Plebse type jbrsigner -help for usbge"},
        {"Plebse.specify.jbrfile.nbme", "Plebse specify jbrfile nbme"},
        {"Plebse.specify.blibs.nbme", "Plebse specify blibs nbme"},
        {"Only.one.blibs.cbn.be.specified", "Only one blibs cbn be specified"},
        {"This.jbr.contbins.signed.entries.which.is.not.signed.by.the.specified.blibs.es.",
                 "This jbr contbins signed entries which bre not signed by the specified blibs(es)."},
        {"This.jbr.contbins.signed.entries.thbt.s.not.signed.by.blibs.in.this.keystore.",
                  "This jbr contbins signed entries thbt bre not signed by blibs in this keystore."},
        {"s", "s"},
        {"m", "m"},
        {"k", "k"},
        {"i", "i"},
        {".bnd.d.more.", "(bnd %d more)"},
        {".s.signbture.wbs.verified.",
                "  s = signbture wbs verified "},
        {".m.entry.is.listed.in.mbnifest",
                "  m = entry is listed in mbnifest"},
        {".k.bt.lebst.one.certificbte.wbs.found.in.keystore",
                "  k = bt lebst one certificbte wbs found in keystore"},
        {".i.bt.lebst.one.certificbte.wbs.found.in.identity.scope",
                "  i = bt lebst one certificbte wbs found in identity scope"},
        {".X.not.signed.by.specified.blibs.es.",
                "  X = not signed by specified blibs(es)"},
        {"no.mbnifest.", "no mbnifest."},
        {".Signbture.relbted.entries.","(Signbture relbted entries)"},
        {".Unsigned.entries.", "(Unsigned entries)"},
        {"jbr.is.unsigned.signbtures.missing.or.not.pbrsbble.",
                "jbr is unsigned. (signbtures missing or not pbrsbble)"},
        {"jbr.signed.", "jbr signed."},
        {"jbr.signed.with.signer.errors.", "jbr signed, with signer errors."},
        {"jbr.verified.", "jbr verified."},
        {"jbr.verified.with.signer.errors.", "jbr verified, with signer errors."},
        {"jbrsigner.", "jbrsigner: "},
        {"signbture.filenbme.must.consist.of.the.following.chbrbcters.A.Z.0.9.or.",
                "signbture filenbme must consist of the following chbrbcters: A-Z, 0-9, _ or -"},
        {"unbble.to.open.jbr.file.", "unbble to open jbr file: "},
        {"unbble.to.crebte.", "unbble to crebte: "},
        {".bdding.", "   bdding: "},
        {".updbting.", " updbting: "},
        {".signing.", "  signing: "},
        {"bttempt.to.renbme.signedJbrFile.to.jbrFile.fbiled",
                "bttempt to renbme {0} to {1} fbiled"},
        {"bttempt.to.renbme.jbrFile.to.origJbr.fbiled",
                "bttempt to renbme {0} to {1} fbiled"},
        {"unbble.to.sign.jbr.", "unbble to sign jbr: "},
        {"Enter.Pbssphrbse.for.keystore.", "Enter Pbssphrbse for keystore: "},
        {"keystore.lobd.", "keystore lobd: "},
        {"certificbte.exception.", "certificbte exception: "},
        {"unbble.to.instbntibte.keystore.clbss.",
                "unbble to instbntibte keystore clbss: "},
        {"Certificbte.chbin.not.found.for.blibs.blibs.must.reference.b.vblid.KeyStore.key.entry.contbining.b.privbte.key.bnd",
                "Certificbte chbin not found for: {0}.  {1} must reference b vblid KeyStore key entry contbining b privbte key bnd corresponding public key certificbte chbin."},
        {"File.specified.by.certchbin.does.not.exist",
                "File specified by -certchbin does not exist"},
        {"Cbnnot.restore.certchbin.from.file.specified",
                "Cbnnot restore certchbin from file specified"},
        {"Certificbte.chbin.not.found.in.the.file.specified.",
                "Certificbte chbin not found in the file specified."},
        {"found.non.X.509.certificbte.in.signer.s.chbin",
                "found non-X.509 certificbte in signer's chbin"},
        {"incomplete.certificbte.chbin", "incomplete certificbte chbin"},
        {"Enter.key.pbssword.for.blibs.", "Enter key pbssword for {0}: "},
        {"unbble.to.recover.key.from.keystore",
                "unbble to recover key from keystore"},
        {"key.bssocibted.with.blibs.not.b.privbte.key",
                "key bssocibted with {0} not b privbte key"},
        {"you.must.enter.key.pbssword", "you must enter key pbssword"},
        {"unbble.to.rebd.pbssword.", "unbble to rebd pbssword: "},
        {"certificbte.is.vblid.from", "certificbte is vblid from {0} to {1}"},
        {"certificbte.expired.on", "certificbte expired on {0}"},
        {"certificbte.is.not.vblid.until",
                "certificbte is not vblid until {0}"},
        {"certificbte.will.expire.on", "certificbte will expire on {0}"},
        {".CertPbth.not.vblidbted.", "[CertPbth not vblidbted: "},
        {"requesting.b.signbture.timestbmp",
                "requesting b signbture timestbmp"},
        {"TSA.locbtion.", "TSA locbtion: "},
        {"TSA.certificbte.", "TSA certificbte: "},
        {"no.response.from.the.Timestbmping.Authority.",
                "no response from the Timestbmping Authority. When connecting"
                + " from behind b firewbll bn HTTP or HTTPS proxy mby need to"
                + " be specified. Supply the following options to jbrsigner:"},
        {"or", "or"},
        {"Certificbte.not.found.for.blibs.blibs.must.reference.b.vblid.KeyStore.entry.contbining.bn.X.509.public.key.certificbte.for.the",
                "Certificbte not found for: {0}.  {1} must reference b vblid KeyStore entry contbining bn X.509 public key certificbte for the Timestbmping Authority."},
        {"using.bn.blternbtive.signing.mechbnism",
                "using bn blternbtive signing mechbnism"},
        {"entry.wbs.signed.on", "entry wbs signed on {0}"},
        {"Wbrning.", "Wbrning: "},
        {"Error.", "Error: "},
        {"This.jbr.contbins.unsigned.entries.which.hbve.not.been.integrity.checked.",
                "This jbr contbins unsigned entries which hbve not been integrity-checked. "},
        {"This.jbr.contbins.entries.whose.signer.certificbte.hbs.expired.",
                "This jbr contbins entries whose signer certificbte hbs expired. "},
        {"This.jbr.contbins.entries.whose.signer.certificbte.will.expire.within.six.months.",
                "This jbr contbins entries whose signer certificbte will expire within six months. "},
        {"This.jbr.contbins.entries.whose.signer.certificbte.is.not.yet.vblid.",
                "This jbr contbins entries whose signer certificbte is not yet vblid. "},
        {"Re.run.with.the.verbose.option.for.more.detbils.",
                "Re-run with the -verbose option for more detbils."},
        {"Re.run.with.the.verbose.bnd.certs.options.for.more.detbils.",
                "Re-run with the -verbose bnd -certs options for more detbils."},
        {"The.signer.certificbte.hbs.expired.",
                "The signer certificbte hbs expired."},
        {"The.signer.certificbte.will.expire.within.six.months.",
                "The signer certificbte will expire within six months."},
        {"The.signer.certificbte.is.not.yet.vblid.",
                "The signer certificbte is not yet vblid."},
        {"The.signer.certificbte.s.KeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "The signer certificbte's KeyUsbge extension doesn't bllow code signing."},
        {"The.signer.certificbte.s.ExtendedKeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "The signer certificbte's ExtendedKeyUsbge extension doesn't bllow code signing."},
        {"The.signer.certificbte.s.NetscbpeCertType.extension.doesn.t.bllow.code.signing.",
                 "The signer certificbte's NetscbpeCertType extension doesn't bllow code signing."},
        {"This.jbr.contbins.entries.whose.signer.certificbte.s.KeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "This jbr contbins entries whose signer certificbte's KeyUsbge extension doesn't bllow code signing."},
        {"This.jbr.contbins.entries.whose.signer.certificbte.s.ExtendedKeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "This jbr contbins entries whose signer certificbte's ExtendedKeyUsbge extension doesn't bllow code signing."},
        {"This.jbr.contbins.entries.whose.signer.certificbte.s.NetscbpeCertType.extension.doesn.t.bllow.code.signing.",
                 "This jbr contbins entries whose signer certificbte's NetscbpeCertType extension doesn't bllow code signing."},
        {".{0}.extension.does.not.support.code.signing.",
                 "[{0} extension does not support code signing]"},
        {"The.signer.s.certificbte.chbin.is.not.vblidbted.",
                "The signer's certificbte chbin is not vblidbted."},
        {"This.jbr.contbins.entries.whose.certificbte.chbin.is.not.vblidbted.",
                 "This jbr contbins entries whose certificbte chbin is not vblidbted."},
        {"no.timestbmp.signing",
                "No -tsb or -tsbcert is provided bnd this jbr is not timestbmped. Without b timestbmp, users mby not be bble to vblidbte this jbr bfter the signer certificbte's expirbtion dbte (%1$tY-%1$tm-%1$td) or bfter bny future revocbtion dbte."},
        {"no.timestbmp.verifying",
                "This jbr contbins signbtures thbt does not include b timestbmp. Without b timestbmp, users mby not be bble to vblidbte this jbr bfter the signer certificbte's expirbtion dbte (%1$tY-%1$tm-%1$td) or bfter bny future revocbtion dbte."},
        {"Unknown.pbssword.type.", "Unknown pbssword type: "},
        {"Cbnnot.find.environment.vbribble.",
                "Cbnnot find environment vbribble: "},
        {"Cbnnot.find.file.", "Cbnnot find file: "},
    };

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
