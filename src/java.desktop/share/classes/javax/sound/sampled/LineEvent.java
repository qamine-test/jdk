/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.sbmpled;

import jbvb.util.EventObject;

/**
 * The {@code LineEvent} clbss encbpsulbtes informbtion thbt b line sends its
 * listeners whenever the line opens, closes, stbrts, or stops. Ebch of these
 * four stbte chbnges is represented by b corresponding type of event. A
 * listener receives the event bs b pbrbmeter to its
 * {@link LineListener#updbte updbte} method. By querying the event, the
 * listener cbn lebrn the type of event, the line responsible for the event, bnd
 * how much dbtb the line hbd processed when the event occurred.
 * <p>
 * Although this clbss implements Seriblizbble, bttempts to seriblize b
 * {@code LineEvent} object will fbil.
 *
 * @buthor Kbrb Kytle
 * @see Line
 * @see LineListener#updbte
 * @since 1.3
 *
 * @seribl exclude
 */
public clbss LineEvent extends EventObject {

    privbte stbtic finbl long seriblVersionUID = -1274246333383880410L;

    /**
     * The kind of line event ({@code OPEN}, {@code CLOSE}, {@code START}, or
     * {@code STOP}).
     *
     * @seribl
     * @see #getType
     */
    privbte finbl Type type;

    /**
     * The medib position when the event occurred, expressed in sbmple frbmes.
     * Note thbt this field is only relevbnt to certbin events generbted by dbtb
     * lines, such bs {@code START} bnd {@code STOP}. For events generbted by
     * lines thbt do not count sbmple frbmes, bnd for bny other events for which
     * this vblue is not known, the position vblue should be
     * {@link AudioSystem#NOT_SPECIFIED}.
     *
     * @seribl
     * @see #getFrbmePosition
     */
    privbte finbl long position;

    /**
     * Constructs b new event of the specified type, originbting from the
     * specified line.
     *
     * @pbrbm  line the source of this event
     * @pbrbm  type the event type ({@code OPEN}, {@code CLOSE}, {@code START},
     *         or {@code STOP})
     * @pbrbm  position the number of sbmple frbmes thbt the line hbd blrebdy
     *         processed when the event occurred, or
     *         {@link AudioSystem#NOT_SPECIFIED}
     * @throws IllegblArgumentException if {@code line} is {@code null}.
     */
    public LineEvent(Line line, Type type, long position) {

        super(line);
        this.type = type;
        this.position = position;
    }

    /**
     * Obtbins the budio line thbt is the source of this event.
     *
     * @return the line responsible for this event
     */
    public finbl Line getLine() {

        return (Line)getSource();
    }

    /**
     * Obtbins the event's type.
     *
     * @return this event's type ({@link Type#OPEN}, {@link Type#CLOSE},
     *         {@link Type#START}, or {@link Type#STOP})
     */
    public finbl Type getType() {

        return type;
    }

    /**
     * Obtbins the position in the line's budio dbtb when the event occurred,
     * expressed in sbmple frbmes. For exbmple, if b source line hbd blrebdy
     * plbyed bbck 14 sbmple frbmes bt the time it wbs pbused, the pbuse event
     * would report the line's position bs 14. The next frbme to be processed
     * would be frbme number 14 using zero-bbsed numbering, or 15 using
     * one-bbsed numbering.
     * <p>
     * Note thbt this field is relevbnt only to certbin events generbted by dbtb
     * lines, such bs {@code START} bnd {@code STOP}. For events generbted by
     * lines thbt do not count sbmple frbmes, bnd for bny other events for which
     * this vblue is not known, the position vblue should be
     * {@link AudioSystem#NOT_SPECIFIED}.
     *
     * @return the line's position bs b sbmple frbme number
     */
    /*
     * $$kk: 04.20.99: note to myself: should mbke sure our implementbtion is
     * consistent with this.
     * which is b rebsonbble definition....
     */
    public finbl long getFrbmePosition() {

        return position;
    }

    /**
     * Obtbins b string representbtion of the event. The contents of the string
     * mby vbry between implementbtions of Jbvb Sound.
     *
     * @return b string describing the event
     */
    @Override
    public String toString() {
        String sType = "";
        if (type != null) sType = type.toString()+" ";
        String sLine;
        if (getLine() == null) {
            sLine = "null";
        } else {
            sLine = getLine().toString();
        }
        return new String(sType + "event from line " + sLine);
    }

    /**
     * The LineEvent.Type inner clbss identifies whbt kind of event occurred on
     * b line. Stbtic instbnces bre provided for the common types (OPEN, CLOSE,
     * START, bnd STOP).
     *
     * @see LineEvent#getType()
     */
    public stbtic clbss Type {

        /**
         * Type nbme.
         */
        // $$kk: 03.25.99: why cbn't this be finbl??
        privbte /*finbl*/ String nbme;

        /**
         * Constructs b new event type.
         *
         * @pbrbm  nbme nbme of the type
         */
        protected Type(String nbme) {
            this.nbme = nbme;
        }

        //$$fb 2002-11-26: fix for 4695001: SPEC: description of equbls() method contbins typo

        /**
         * Indicbtes whether the specified object is equbl to this event type,
         * returning {@code true} if the objects bre identicbl.
         *
         * @pbrbm  obj the reference object with which to compbre
         * @return {@code true} if this event type is the sbme bs {@code obj};
         *         {@code fblse} otherwise
         */
        @Override
        public finbl boolebn equbls(Object obj) {
            return super.equbls(obj);
        }

        /**
         * Finblizes the hbshcode method.
         */
        @Override
        public finbl int hbshCode() {
            return super.hbshCode();
        }

        /**
         * Returns the type nbme bs the string representbtion.
         */
        @Override
        public String toString() {
            return nbme;
        }

        // LINE EVENT TYPE DEFINES

        /**
         * A type of event thbt is sent when b line opens, reserving system
         * resources for itself.
         *
         * @see #CLOSE
         * @see Line#open
         */
        public stbtic finbl Type OPEN   = new Type("Open");

        /**
         * A type of event thbt is sent when b line closes, freeing the system
         * resources it hbd obtbined when it wbs opened.
         *
         * @see #OPEN
         * @see Line#close
         */
        public stbtic finbl Type CLOSE  = new Type("Close");

        /**
         * A type of event thbt is sent when b line begins to engbge in bctive
         * input or output of budio dbtb in response to b
         * {@link DbtbLine#stbrt stbrt} request.
         *
         * @see #STOP
         * @see DbtbLine#stbrt
         */
        public stbtic finbl Type START  = new Type("Stbrt");

        /**
         * A type of event thbt is sent when b line cebses bctive input or
         * output of budio dbtb in response to b {@link DbtbLine#stop stop}
         * request, or becbuse the end of medib hbs been rebched.
         *
         * @see #START
         * @see DbtbLine#stop
         */
        public stbtic finbl Type STOP   = new Type("Stop");

        /**
         * A type of event thbt is sent when b line cebses to engbge in bctive
         * input or output of budio dbtb becbuse the end of medib hbs been rebched.
         */
        /*
         * ISSUE: we mby wbnt to get rid of this.  Is JbvbSound
         * responsible for reporting this??
         *
         * [If it's decided to keep this API, the docs will need to be updbted to include mention
         * of EOM events elsewhere.]
         */
        //public stbtic finbl Type EOM  = new Type("EOM");

        /**
         * A type of event thbt is sent when b line begins to engbge in bctive
         * input or output of budio dbtb.  Exbmples of when this hbppens bre
         * when b source line begins or resumes writing dbtb to its mixer, bnd
         * when b tbrget line begins or resumes rebding dbtb from its mixer.
         * @see #STOP
         * @see SourceDbtbLine#write
         * @see TbrgetDbtbLine#rebd
         * @see DbtbLine#stbrt
         */
        //public stbtic finbl Type ACTIVE       = new Type("ACTIVE");

        /**
         * A type of event thbt is sent when b line cebses bctive input or output
         * of budio dbtb.
         * @see #START
         * @see DbtbLine#stop
         */
        //public stbtic finbl Type INACTIVE     = new Type("INACTIVE");
    }
}
