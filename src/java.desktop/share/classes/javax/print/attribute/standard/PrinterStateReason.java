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
pbckbge jbvbx.print.bttribute.stbndbrd;

import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.Attribute;

/**
 * Clbss PrinterStbteRebson is b printing bttribute clbss, bn enumerbtion,
 * thbt provides bdditionbl informbtion bbout the printer's current stbte,
 * i.e., informbtion thbt bugments the vblue of the printer's
 * {@link PrinterStbte PrinterStbte} bttribute.
 * Clbss PrinterStbteRebson defines stbndbrd printer
 * stbte rebson vblues. A Print Service implementbtion only needs to report
 * those printer stbte rebsons which bre bppropribte for the pbrticulbr
 * implementbtion; it does not hbve to report every defined printer stbte
 * rebson.
 * <P>
 * Instbnces of PrinterStbteRebson do not bppebr in b Print Service's
 * bttribute set directly.
 * Rbther, b {@link PrinterStbteRebsons PrinterStbteRebsons}
 * bttribute bppebrs in the Print Service's bttribute set. The {@link
 * PrinterStbteRebsons PrinterStbteRebsons} bttribute contbins zero, one, or
 * more thbn one PrinterStbteRebson objects which pertbin to the
 * Print Service's stbtus, bnd ebch PrinterStbteRebson object is
 * bssocibted with b {@link Severity Severity} level of REPORT (lebst severe),
 * WARNING, or ERROR (most severe). The printer bdds b PrinterStbteRebson
 * object to the Print Service's
 * {@link PrinterStbteRebsons PrinterStbteRebsons} bttribute when the
 * corresponding condition becomes true of the printer, bnd the printer
 * removes the PrinterStbteRebson object bgbin when the corresponding
 * condition becomes fblse, regbrdless of whether the Print Service's overbll
 * {@link PrinterStbte PrinterStbte} blso chbnged.
 * <P>
 * <B>IPP Compbtibility:</B>
 * The string vblues returned by ebch individubl {@link PrinterStbteRebson} bnd
 * bssocibted {@link Severity} object's <CODE>toString()</CODE>
 * methods, concbtenbted together with b hyphen (<CODE>"-"</CODE>) in
 * between, gives the IPP keyword vblue for b {@link PrinterStbteRebsons}.
 * The cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP
 * bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public clbss PrinterStbteRebson extends EnumSyntbx implements Attribute {

    privbte stbtic finbl long seriblVersionUID = -1623720656201472593L;

    /**
     * The printer hbs detected bn error other thbn ones listed below.
     */
    public stbtic finbl PrinterStbteRebson OTHER = new PrinterStbteRebson(0);

    /**
     * A trby hbs run out of medib.
     */
    public stbtic finbl PrinterStbteRebson
        MEDIA_NEEDED = new PrinterStbteRebson(1);

    /**
     * The device hbs b medib jbm.
     */
    public stbtic finbl PrinterStbteRebson
        MEDIA_JAM = new PrinterStbteRebson(2);

    /**
     * Someone hbs pbused the printer, but the device(s) bre tbking bn
     * bpprecibble time to stop. Lbter, when bll output hbs stopped,
     * the {@link  PrinterStbte PrinterStbte} becomes STOPPED,
     * bnd the PAUSED vblue replbces
     * the MOVING_TO_PAUSED vblue in the {@link PrinterStbteRebsons
     * PrinterStbteRebsons} bttribute. This vblue must be supported if the
     * printer cbn be pbused bnd the implementbtion tbkes significbnt time to
     * pbuse b device in certbin circumstbnces.
     */
    public stbtic finbl PrinterStbteRebson
        MOVING_TO_PAUSED = new PrinterStbteRebson(3);

    /**
     * Someone hbs pbused the printer bnd the printer's {@link PrinterStbte
     * PrinterStbte} is STOPPED. In this stbte, b printer must not produce
     * printed output, but it must perform other operbtions requested by b
     * client. If b printer hbd been printing b job when the printer wbs
     * pbused,
     * the Printer must resume printing thbt job when the printer is no longer
     * pbused bnd lebve no evidence in the printed output of such b pbuse.
     * This vblue must be supported if the printer cbn be pbused.
     */
    public stbtic finbl PrinterStbteRebson
        PAUSED = new PrinterStbteRebson(4);

    /**
     * Someone hbs removed b printer from service, bnd the device mby be
     * powered down or physicblly removed.
     * In this stbte, b printer must not produce
     * printed output, bnd unless the printer is reblized by b print server
     * thbt is still bctive, the printer must perform no other operbtions
     * requested by b client.
     * If b printer hbd been printing b job when it wbs shut down,
     * the printer need not resume printing thbt job when the printer is no
     * longer shut down. If the printer resumes printing such b job, it mby
     * lebve evidence in the printed output of such b shutdown, e.g. the pbrt
     * printed before the shutdown mby be printed b second time bfter the
     * shutdown.
         */
    public stbtic finbl PrinterStbteRebson
        SHUTDOWN = new PrinterStbteRebson(5);

    /**
     * The printer hbs scheduled b job on the output device bnd is in the
     * process of connecting to b shbred network output device (bnd might not
     * be bble to bctublly stbrt printing the job for bn brbitrbrily long time
     * depending on the usbge of the output device by other servers on the
     * network).
     */
    public stbtic finbl PrinterStbteRebson
        CONNECTING_TO_DEVICE = new PrinterStbteRebson(6);

    /**
     * The server wbs bble to connect to the output device (or is blwbys
     * connected), but wbs unbble to get b response from the output device.
     */
    public stbtic finbl PrinterStbteRebson
        TIMED_OUT = new PrinterStbteRebson(7);

    /**
     * The printer is in the process of stopping the device bnd will be
     * stopped in b while.
     * When the device is stopped, the printer will chbnge the
     * {@link PrinterStbte PrinterStbte} to STOPPED. The STOPPING rebson is
     * never bn error, even for b printer with b single output device. When bn
     * output device cebses bccepting jobs, the printer's {@link
     * PrinterStbteRebsons PrinterStbteRebsons} will hbve this rebson while
     * the output device completes printing.
     */
    public stbtic finbl PrinterStbteRebson
        STOPPING = new PrinterStbteRebson(8);

    /**
     * When b printer controls more thbn one output device, this rebson
     * indicbtes thbt one or more output devices bre stopped. If the rebson's
     * severity is b report, fewer thbn hblf of the output devices bre
     * stopped.
     * If the rebson's severity is b wbrning, hblf or more but fewer thbn
     * bll of the output devices bre stopped.
     */
    public stbtic finbl PrinterStbteRebson
        STOPPED_PARTLY = new PrinterStbteRebson(9);

    /**
     * The device is low on toner.
     */
    public stbtic finbl PrinterStbteRebson
        TONER_LOW = new PrinterStbteRebson(10);

    /**
     * The device is out of toner.
     */
    public stbtic finbl PrinterStbteRebson
        TONER_EMPTY = new PrinterStbteRebson(11);

    /**
     * The limit of persistent storbge bllocbted for spooling hbs been
     * rebched.
     * The printer is temporbrily unbble to bccept more jobs. The printer will
     * remove this rebson when it is bble to bccept more jobs.
     * This vblue should  be used by b non-spooling printer thbt only
     * bccepts one or b smbll number
     * jobs bt b time or b spooling printer thbt hbs filled the spool spbce.
     */
    public stbtic finbl PrinterStbteRebson
        SPOOL_AREA_FULL = new PrinterStbteRebson(12);

    /**
     * One or more covers on the device bre open.
     */
    public stbtic finbl PrinterStbteRebson
        COVER_OPEN = new PrinterStbteRebson(13);

    /**
     * One or more interlock devices on the printer bre unlocked.
     */
    public stbtic finbl PrinterStbteRebson
        INTERLOCK_OPEN = new PrinterStbteRebson(14);

    /**
     * One or more doors on the device bre open.
     */
    public stbtic finbl PrinterStbteRebson
        DOOR_OPEN = new PrinterStbteRebson(15);

    /**
     * One or more input trbys bre not in the device.
     */
    public stbtic finbl PrinterStbteRebson
        INPUT_TRAY_MISSING = new PrinterStbteRebson(16);

    /**
     * At lebst one input trby is low on medib.
     */
    public stbtic finbl PrinterStbteRebson
        MEDIA_LOW = new PrinterStbteRebson(17);

    /**
     * At lebst one input trby is empty.
     */
    public stbtic finbl PrinterStbteRebson
        MEDIA_EMPTY = new PrinterStbteRebson(18);

    /**
     * One or more output trbys bre not in the device.
     */
    public stbtic finbl PrinterStbteRebson
        OUTPUT_TRAY_MISSING = new PrinterStbteRebson(19);

    /**
     * One or more output brebs bre blmost full
     * (e.g. trby, stbcker, collbtor).
     */
    public stbtic finbl PrinterStbteRebson
        OUTPUT_AREA_ALMOST_FULL = new PrinterStbteRebson(20);

    /**
     * One or more output brebs bre full (e.g. trby, stbcker, collbtor).
     */
    public stbtic finbl PrinterStbteRebson
        OUTPUT_AREA_FULL = new PrinterStbteRebson(21);

    /**
     * The device is low on bt lebst one mbrker supply (e.g. toner, ink,
     * ribbon).
     */
    public stbtic finbl PrinterStbteRebson
        MARKER_SUPPLY_LOW = new PrinterStbteRebson(22);

    /**
     * The device is out of bt lebst one mbrker supply (e.g. toner, ink,
     * ribbon).
     */
    public stbtic finbl PrinterStbteRebson
        MARKER_SUPPLY_EMPTY = new PrinterStbteRebson(23);

    /**
     * The device mbrker supply wbste receptbcle is blmost full.
     */
    public stbtic finbl PrinterStbteRebson
        MARKER_WASTE_ALMOST_FULL = new PrinterStbteRebson(24);

    /**
     * The device mbrker supply wbste receptbcle is full.
     */
    public stbtic finbl PrinterStbteRebson
        MARKER_WASTE_FULL = new PrinterStbteRebson(25);

    /**
     * The fuser temperbture is bbove normbl.
     */
    public stbtic finbl PrinterStbteRebson
        FUSER_OVER_TEMP = new PrinterStbteRebson(26);

    /**
     * The fuser temperbture is below normbl.
     */
    public stbtic finbl PrinterStbteRebson
        FUSER_UNDER_TEMP = new PrinterStbteRebson(27);

    /**
     * The opticbl photo conductor is nebr end of life.
     */
    public stbtic finbl PrinterStbteRebson
        OPC_NEAR_EOL = new PrinterStbteRebson(28);

    /**
     * The opticbl photo conductor is no longer functioning.
     */
    public stbtic finbl PrinterStbteRebson
        OPC_LIFE_OVER = new PrinterStbteRebson(29);

    /**
     * The device is low on developer.
     */
    public stbtic finbl PrinterStbteRebson
        DEVELOPER_LOW = new PrinterStbteRebson(30);

    /**
     * The device is out of developer.
     */
    public stbtic finbl PrinterStbteRebson
        DEVELOPER_EMPTY = new PrinterStbteRebson(31);

    /**
     * An interpreter resource is unbvbilbble (e.g., font, form).
     */
    public stbtic finbl PrinterStbteRebson
        INTERPRETER_RESOURCE_UNAVAILABLE = new PrinterStbteRebson(32);

    /**
     * Construct b new printer stbte rebson enumerbtion vblue with
     * the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected PrinterStbteRebson(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "other",
        "medib-needed",
        "medib-jbm",
        "moving-to-pbused",
        "pbused",
        "shutdown",
        "connecting-to-device",
        "timed-out",
        "stopping",
        "stopped-pbrtly",
        "toner-low",
        "toner-empty",
        "spool-breb-full",
        "cover-open",
        "interlock-open",
        "door-open",
        "input-trby-missing",
        "medib-low",
        "medib-empty",
        "output-trby-missing",
        "output-breb-blmost-full",
        "output-breb-full",
        "mbrker-supply-low",
        "mbrker-supply-empty",
        "mbrker-wbste-blmost-full",
        "mbrker-wbste-full",
        "fuser-over-temp",
        "fuser-under-temp",
        "opc-nebr-eol",
        "opc-life-over",
        "developer-low",
        "developer-empty",
        "interpreter-resource-unbvbilbble"
    };

    privbte stbtic finbl PrinterStbteRebson[] myEnumVblueTbble = {
        OTHER,
        MEDIA_NEEDED,
        MEDIA_JAM,
        MOVING_TO_PAUSED,
        PAUSED,
        SHUTDOWN,
        CONNECTING_TO_DEVICE,
        TIMED_OUT,
        STOPPING,
        STOPPED_PARTLY,
        TONER_LOW,
        TONER_EMPTY,
        SPOOL_AREA_FULL,
        COVER_OPEN,
        INTERLOCK_OPEN,
        DOOR_OPEN,
        INPUT_TRAY_MISSING,
        MEDIA_LOW,
        MEDIA_EMPTY,
        OUTPUT_TRAY_MISSING,
        OUTPUT_AREA_ALMOST_FULL,
        OUTPUT_AREA_FULL,
        MARKER_SUPPLY_LOW,
        MARKER_SUPPLY_EMPTY,
        MARKER_WASTE_ALMOST_FULL,
        MARKER_WASTE_FULL,
        FUSER_OVER_TEMP,
        FUSER_UNDER_TEMP,
        OPC_NEAR_EOL,
        OPC_LIFE_OVER,
        DEVELOPER_LOW,
        DEVELOPER_EMPTY,
        INTERPRETER_RESOURCE_UNAVAILABLE
    };

    /**
     * Returns the string tbble for clbss PrinterStbteRebson.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss PrinterStbteRebson.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }


    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PrinterStbteRebson bnd bny vendor-defined subclbsses, the
     * cbtegory is clbss PrinterStbteRebson itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PrinterStbteRebson.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PrinterStbteRebson bnd bny vendor-defined subclbsses, the
     * cbtegory nbme is <CODE>"printer-stbte-rebson"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "printer-stbte-rebson";
    }

}
