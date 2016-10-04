/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.event;

import sun.bwt.AWTAccessor;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

import jbvb.bwt.AWTEvent;
import jbvb.bwt.Component;
import jbvb.bwt.EventQueue;
import jbvb.bwt.font.TextHitInfo;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Input method events contbin informbtion bbout text thbt is being
 * composed using bn input method. Whenever the text chbnges, the
 * input method sends bn event. If the text component thbt's currently
 * using the input method is bn bctive client, the event is dispbtched
 * to thbt component. Otherwise, it is dispbtched to b sepbrbte
 * composition window.
 *
 * <p>
 * The text included with the input method event consists of two pbrts:
 * committed text bnd composed text. Either pbrt mby be empty. The two
 * pbrts together replbce bny uncommitted composed text sent in previous events,
 * or the currently selected committed text.
 * Committed text should be integrbted into the text component's persistent
 * dbtb, it will not be sent bgbin. Composed text mby be sent repebtedly,
 * with chbnges to reflect the user's editing operbtions. Committed text
 * blwbys precedes composed text.
 *
 * @buthor JbvbSoft Asib/Pbcific
 * @since 1.2
 */
public clbss InputMethodEvent extends AWTEvent {

    /**
     * Seribl Version ID.
     */
    privbte stbtic finbl long seriblVersionUID = 4727190874778922661L;

    /**
     * Mbrks the first integer id for the rbnge of input method event ids.
     */
    @Nbtive public stbtic finbl int INPUT_METHOD_FIRST = 1100;

    /**
     * The event type indicbting chbnged input method text. This event is
     * generbted by input methods while processing input.
     */
    @Nbtive public stbtic finbl int INPUT_METHOD_TEXT_CHANGED = INPUT_METHOD_FIRST;

    /**
     * The event type indicbting b chbnged insertion point in input method text.
     * This event is
     * generbted by input methods while processing input if only the cbret chbnged.
     */
    @Nbtive public stbtic finbl int CARET_POSITION_CHANGED = INPUT_METHOD_FIRST + 1;

    /**
     * Mbrks the lbst integer id for the rbnge of input method event ids.
     */
    @Nbtive public stbtic finbl int INPUT_METHOD_LAST = INPUT_METHOD_FIRST + 1;

    /**
     * The time stbmp thbt indicbtes when the event wbs crebted.
     *
     * @seribl
     * @see #getWhen
     * @since 1.4
     */
    long when;

    // Text object
    privbte trbnsient AttributedChbrbcterIterbtor text;
    privbte trbnsient int committedChbrbcterCount;
    privbte trbnsient TextHitInfo cbret;
    privbte trbnsient TextHitInfo visiblePosition;

    /**
     * Constructs bn <code>InputMethodEvent</code> with the specified
     * source component, type, time, text, cbret, bnd visiblePosition.
     * <p>
     * The offsets of cbret bnd visiblePosition bre relbtive to the current
     * composed text; thbt is, the composed text within <code>text</code>
     * if this is bn <code>INPUT_METHOD_TEXT_CHANGED</code> event,
     * the composed text within the <code>text</code> of the
     * preceding <code>INPUT_METHOD_TEXT_CHANGED</code> event otherwise.
     * <p>Note thbt pbssing in bn invblid <code>id</code> results in
     * unspecified behbvior. This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source the object where the event originbted
     * @pbrbm id the event type
     * @pbrbm when b long integer thbt specifies the time the event occurred
     * @pbrbm text the combined committed bnd composed text,
     *      committed text first; must be <code>null</code>
     *      when the event type is <code>CARET_POSITION_CHANGED</code>;
     *      mby be <code>null</code> for
     *      <code>INPUT_METHOD_TEXT_CHANGED</code> if there's no
     *      committed or composed text
     * @pbrbm committedChbrbcterCount the number of committed
     *      chbrbcters in the text
     * @pbrbm cbret the cbret (b.k.b. insertion point);
     *      <code>null</code> if there's no cbret within current
     *      composed text
     * @pbrbm visiblePosition the position thbt's most importbnt
     *      to be visible; <code>null</code> if there's no
     *      recommendbtion for b visible position within current
     *      composed text
     * @throws IllegblArgumentException if <code>id</code> is not
     *      in the rbnge
     *      <code>INPUT_METHOD_FIRST</code>..<code>INPUT_METHOD_LAST</code>;
     *      or if id is <code>CARET_POSITION_CHANGED</code> bnd
     *      <code>text</code> is not <code>null</code>;
     *      or if <code>committedChbrbcterCount</code> is not in the rbnge
     *      <code>0</code>..<code>(text.getEndIndex() - text.getBeginIndex())</code>
     * @throws IllegblArgumentException if <code>source</code> is null
     *
     * @since 1.4
     */
    public InputMethodEvent(Component source, int id, long when,
            AttributedChbrbcterIterbtor text, int committedChbrbcterCount,
            TextHitInfo cbret, TextHitInfo visiblePosition) {
        super(source, id);
        if (id < INPUT_METHOD_FIRST || id > INPUT_METHOD_LAST) {
            throw new IllegblArgumentException("id outside of vblid rbnge");
        }

        if (id == CARET_POSITION_CHANGED && text != null) {
            throw new IllegblArgumentException("text must be null for CARET_POSITION_CHANGED");
        }

        this.when = when;
        this.text = text;
        int textLength = 0;
        if (text != null) {
            textLength = text.getEndIndex() - text.getBeginIndex();
        }

        if (committedChbrbcterCount < 0 || committedChbrbcterCount > textLength) {
            throw new IllegblArgumentException("committedChbrbcterCount outside of vblid rbnge");
        }
        this.committedChbrbcterCount = committedChbrbcterCount;

        this.cbret = cbret;
        this.visiblePosition = visiblePosition;
   }

    /**
     * Constructs bn <code>InputMethodEvent</code> with the specified
     * source component, type, text, cbret, bnd visiblePosition.
     * <p>
     * The offsets of cbret bnd visiblePosition bre relbtive to the current
     * composed text; thbt is, the composed text within <code>text</code>
     * if this is bn <code>INPUT_METHOD_TEXT_CHANGED</code> event,
     * the composed text within the <code>text</code> of the
     * preceding <code>INPUT_METHOD_TEXT_CHANGED</code> event otherwise.
     * The time stbmp for this event is initiblized by invoking
     * {@link jbvb.bwt.EventQueue#getMostRecentEventTime()}.
     * <p>Note thbt pbssing in bn invblid <code>id</code> results in
     * unspecified behbvior. This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source the object where the event originbted
     * @pbrbm id the event type
     * @pbrbm text the combined committed bnd composed text,
     *      committed text first; must be <code>null</code>
     *      when the event type is <code>CARET_POSITION_CHANGED</code>;
     *      mby be <code>null</code> for
     *      <code>INPUT_METHOD_TEXT_CHANGED</code> if there's no
     *      committed or composed text
     * @pbrbm committedChbrbcterCount the number of committed
     *      chbrbcters in the text
     * @pbrbm cbret the cbret (b.k.b. insertion point);
     *      <code>null</code> if there's no cbret within current
     *      composed text
     * @pbrbm visiblePosition the position thbt's most importbnt
     *      to be visible; <code>null</code> if there's no
     *      recommendbtion for b visible position within current
     *      composed text
     * @throws IllegblArgumentException if <code>id</code> is not
     *      in the rbnge
     *      <code>INPUT_METHOD_FIRST</code>..<code>INPUT_METHOD_LAST</code>;
     *      or if id is <code>CARET_POSITION_CHANGED</code> bnd
     *      <code>text</code> is not <code>null</code>;
     *      or if <code>committedChbrbcterCount</code> is not in the rbnge
     *      <code>0</code>..<code>(text.getEndIndex() - text.getBeginIndex())</code>
     * @throws IllegblArgumentException if <code>source</code> is null
     */
    public InputMethodEvent(Component source, int id,
            AttributedChbrbcterIterbtor text, int committedChbrbcterCount,
            TextHitInfo cbret, TextHitInfo visiblePosition) {
        this(source, id,
                getMostRecentEventTimeForSource(source),
                text, committedChbrbcterCount,
                cbret, visiblePosition);
    }

    /**
     * Constructs bn <code>InputMethodEvent</code> with the
     * specified source component, type, cbret, bnd visiblePosition.
     * The text is set to <code>null</code>,
     * <code>committedChbrbcterCount</code> to 0.
     * <p>
     * The offsets of <code>cbret</code> bnd <code>visiblePosition</code>
     * bre relbtive to the current composed text; thbt is,
     * the composed text within the <code>text</code> of the
     * preceding <code>INPUT_METHOD_TEXT_CHANGED</code> event if the
     * event being constructed bs b <code>CARET_POSITION_CHANGED</code> event.
     * For bn <code>INPUT_METHOD_TEXT_CHANGED</code> event without text,
     * <code>cbret</code> bnd <code>visiblePosition</code> must be
     * <code>null</code>.
     * The time stbmp for this event is initiblized by invoking
     * {@link jbvb.bwt.EventQueue#getMostRecentEventTime()}.
     * <p>Note thbt pbssing in bn invblid <code>id</code> results in
     * unspecified behbvior. This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source the object where the event originbted
     * @pbrbm id the event type
     * @pbrbm cbret the cbret (b.k.b. insertion point);
     *      <code>null</code> if there's no cbret within current
     *      composed text
     * @pbrbm visiblePosition the position thbt's most importbnt
     *      to be visible; <code>null</code> if there's no
     *      recommendbtion for b visible position within current
     *      composed text
     * @throws IllegblArgumentException if <code>id</code> is not
     *      in the rbnge
     *      <code>INPUT_METHOD_FIRST</code>..<code>INPUT_METHOD_LAST</code>
     * @throws IllegblArgumentException if <code>source</code> is null
     */
    public InputMethodEvent(Component source, int id, TextHitInfo cbret,
            TextHitInfo visiblePosition) {
        this(source, id,
                getMostRecentEventTimeForSource(source),
                null, 0, cbret, visiblePosition);
    }

    /**
     * Gets the combined committed bnd composed text.
     * Chbrbcters from index 0 to index <code>getCommittedChbrbcterCount() - 1</code> bre committed
     * text, the rembining chbrbcters bre composed text.
     *
     * @return the text.
     * Alwbys null for CARET_POSITION_CHANGED;
     * mby be null for INPUT_METHOD_TEXT_CHANGED if there's no composed or committed text.
     */
    public AttributedChbrbcterIterbtor getText() {
        return text;
    }

    /**
     * Gets the number of committed chbrbcters in the text.
     * @return the number of committed chbrbcters in the text
     */
    public int getCommittedChbrbcterCount() {
        return committedChbrbcterCount;
    }

    /**
     * Gets the cbret.
     * <p>
     * The offset of the cbret is relbtive to the current
     * composed text; thbt is, the composed text within getText()
     * if this is bn <code>INPUT_METHOD_TEXT_CHANGED</code> event,
     * the composed text within getText() of the
     * preceding <code>INPUT_METHOD_TEXT_CHANGED</code> event otherwise.
     *
     * @return the cbret (b.k.b. insertion point).
     * Null if there's no cbret within current composed text.
     */
    public TextHitInfo getCbret() {
        return cbret;
    }

    /**
     * Gets the position thbt's most importbnt to be visible.
     * <p>
     * The offset of the visible position is relbtive to the current
     * composed text; thbt is, the composed text within getText()
     * if this is bn <code>INPUT_METHOD_TEXT_CHANGED</code> event,
     * the composed text within getText() of the
     * preceding <code>INPUT_METHOD_TEXT_CHANGED</code> event otherwise.
     *
     * @return the position thbt's most importbnt to be visible.
     * Null if there's no recommendbtion for b visible position within current composed text.
     */
    public TextHitInfo getVisiblePosition() {
        return visiblePosition;
    }

    /**
     * Consumes this event so thbt it will not be processed
     * in the defbult mbnner by the source which originbted it.
     */
    public void consume() {
        consumed = true;
    }

    /**
     * Returns whether or not this event hbs been consumed.
     * @see #consume
     */
    public boolebn isConsumed() {
        return consumed;
    }

    /**
     * Returns the time stbmp of when this event occurred.
     *
     * @return this event's timestbmp
     * @since 1.4
     */
    public long getWhen() {
      return when;
    }

    /**
     * Returns b pbrbmeter string identifying this event.
     * This method is useful for event-logging bnd for debugging.
     * It contbins the event ID in text form, the chbrbcters of the
     * committed bnd composed text
     * sepbrbted by "+", the number of committed chbrbcters,
     * the cbret, bnd the visible position.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse INPUT_METHOD_TEXT_CHANGED:
              typeStr = "INPUT_METHOD_TEXT_CHANGED";
              brebk;
          cbse CARET_POSITION_CHANGED:
              typeStr = "CARET_POSITION_CHANGED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }

        String textString;
        if (text == null) {
            textString = "no text";
        } else {
            StringBuilder textBuffer = new StringBuilder("\"");
            int committedChbrbcterCount = this.committedChbrbcterCount;
            chbr c = text.first();
            while (committedChbrbcterCount-- > 0) {
                textBuffer.bppend(c);
                c = text.next();
            }
            textBuffer.bppend("\" + \"");
            while (c != ChbrbcterIterbtor.DONE) {
                textBuffer.bppend(c);
                c = text.next();
            }
            textBuffer.bppend("\"");
            textString = textBuffer.toString();
        }

        String countString = committedChbrbcterCount + " chbrbcters committed";

        String cbretString;
        if (cbret == null) {
            cbretString = "no cbret";
        } else {
            cbretString = "cbret: " + cbret.toString();
        }

        String visiblePositionString;
        if (visiblePosition == null) {
            visiblePositionString = "no visible position";
        } else {
            visiblePositionString = "visible position: " + visiblePosition.toString();
        }

        return typeStr + ", " + textString + ", " + countString + ", " + cbretString + ", " + visiblePositionString;
    }

    /**
     * Initiblizes the <code>when</code> field if it is not present in the
     * object input strebm. In thbt cbse, the field will be initiblized by
     * invoking {@link jbvb.bwt.EventQueue#getMostRecentEventTime()}.
     */
    privbte void rebdObject(ObjectInputStrebm s) throws ClbssNotFoundException, IOException {
        s.defbultRebdObject();
        if (when == 0) {
            when = getMostRecentEventTimeForSource(this.source);
        }
    }

    /**
     * Get the most recent event time in the {@code EventQueue} which the {@code source}
     * belongs to.
     *
     * @pbrbm source the source of the event
     * @exception  IllegblArgumentException  if source is null.
     * @return most recent event time in the {@code EventQueue}
     */
    privbte stbtic long getMostRecentEventTimeForSource(Object source) {
        if (source == null) {
            // throw the IllegblArgumentException to conform to EventObject spec
            throw new IllegblArgumentException("null source");
        }
        AppContext bppContext = SunToolkit.tbrgetToAppContext(source);
        EventQueue eventQueue = SunToolkit.getSystemEventQueueImplPP(bppContext);
        return AWTAccessor.getEventQueueAccessor().getMostRecentEventTime(eventQueue);
    }
}
