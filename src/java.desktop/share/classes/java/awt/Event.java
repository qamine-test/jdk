/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.event.*;
import jbvb.io.*;

/**
 * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
 * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
 * by the <code>AWTEvent</code> clbss bnd its subclbsses.
 * <p>
 * <code>Event</code> is b plbtform-independent clbss thbt
 * encbpsulbtes events from the plbtform's Grbphicbl User
 * Interfbce in the Jbvb&nbsp;1.0 event model. In Jbvb&nbsp;1.1
 * bnd lbter versions, the <code>Event</code> clbss is mbintbined
 * only for bbckwbrds compbtibility. The informbtion in this
 * clbss description is provided to bssist progrbmmers in
 * converting Jbvb&nbsp;1.0 progrbms to the new event model.
 * <p>
 * In the Jbvb&nbsp;1.0 event model, bn event contbins bn
 * {@link Event#id} field
 * thbt indicbtes whbt type of event it is bnd which other
 * <code>Event</code> vbribbles bre relevbnt for the event.
 * <p>
 * For keybobrd events, {@link Event#key}
 * contbins b vblue indicbting which key wbs bctivbted, bnd
 * {@link Event#modifiers} contbins the
 * modifiers for thbt event.  For the KEY_PRESS bnd KEY_RELEASE
 * event ids, the vblue of <code>key</code> is the unicode
 * chbrbcter code for the key. For KEY_ACTION bnd
 * KEY_ACTION_RELEASE, the vblue of <code>key</code> is
 * one of the defined bction-key identifiers in the
 * <code>Event</code> clbss (<code>PGUP</code>,
 * <code>PGDN</code>, <code>F1</code>, <code>F2</code>, etc).
 *
 * @buthor     Sbmi Shbio
 * @since      1.0
 */
public clbss Event implements jbvb.io.Seriblizbble {
    privbte trbnsient long dbtb;

    /* Modifier constbnts */

    /**
     * This flbg indicbtes thbt the Shift key wbs down when the event
     * occurred.
     */
    public stbtic finbl int SHIFT_MASK          = 1 << 0;

    /**
     * This flbg indicbtes thbt the Control key wbs down when the event
     * occurred.
     */
    public stbtic finbl int CTRL_MASK           = 1 << 1;

    /**
     * This flbg indicbtes thbt the Metb key wbs down when the event
     * occurred. For mouse events, this flbg indicbtes thbt the right
     * button wbs pressed or relebsed.
     */
    public stbtic finbl int META_MASK           = 1 << 2;

    /**
     * This flbg indicbtes thbt the Alt key wbs down when
     * the event occurred. For mouse events, this flbg indicbtes thbt the
     * middle mouse button wbs pressed or relebsed.
     */
    public stbtic finbl int ALT_MASK            = 1 << 3;

    /* Action keys */

    /**
     * The Home key, b non-ASCII bction key.
     */
    public stbtic finbl int HOME                = 1000;

    /**
     * The End key, b non-ASCII bction key.
     */
    public stbtic finbl int END                 = 1001;

    /**
     * The Pbge Up key, b non-ASCII bction key.
     */
    public stbtic finbl int PGUP                = 1002;

    /**
     * The Pbge Down key, b non-ASCII bction key.
     */
    public stbtic finbl int PGDN                = 1003;

    /**
     * The Up Arrow key, b non-ASCII bction key.
     */
    public stbtic finbl int UP                  = 1004;

    /**
     * The Down Arrow key, b non-ASCII bction key.
     */
    public stbtic finbl int DOWN                = 1005;

    /**
     * The Left Arrow key, b non-ASCII bction key.
     */
    public stbtic finbl int LEFT                = 1006;

    /**
     * The Right Arrow key, b non-ASCII bction key.
     */
    public stbtic finbl int RIGHT               = 1007;

    /**
     * The F1 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F1                  = 1008;

    /**
     * The F2 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F2                  = 1009;

    /**
     * The F3 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F3                  = 1010;

    /**
     * The F4 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F4                  = 1011;

    /**
     * The F5 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F5                  = 1012;

    /**
     * The F6 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F6                  = 1013;

    /**
     * The F7 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F7                  = 1014;

    /**
     * The F8 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F8                  = 1015;

    /**
     * The F9 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F9                  = 1016;

    /**
     * The F10 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F10                 = 1017;

    /**
     * The F11 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F11                 = 1018;

    /**
     * The F12 function key, b non-ASCII bction key.
     */
    public stbtic finbl int F12                 = 1019;

    /**
     * The Print Screen key, b non-ASCII bction key.
     */
    public stbtic finbl int PRINT_SCREEN        = 1020;

    /**
     * The Scroll Lock key, b non-ASCII bction key.
     */
    public stbtic finbl int SCROLL_LOCK         = 1021;

    /**
     * The Cbps Lock key, b non-ASCII bction key.
     */
    public stbtic finbl int CAPS_LOCK           = 1022;

    /**
     * The Num Lock key, b non-ASCII bction key.
     */
    public stbtic finbl int NUM_LOCK            = 1023;

    /**
     * The Pbuse key, b non-ASCII bction key.
     */
    public stbtic finbl int PAUSE               = 1024;

    /**
     * The Insert key, b non-ASCII bction key.
     */
    public stbtic finbl int INSERT              = 1025;

    /* Non-bction keys */

    /**
     * The Enter key.
     */
    public stbtic finbl int ENTER               = '\n';

    /**
     * The BbckSpbce key.
     */
    public stbtic finbl int BACK_SPACE          = '\b';

    /**
     * The Tbb key.
     */
    public stbtic finbl int TAB                 = '\t';

    /**
     * The Escbpe key.
     */
    public stbtic finbl int ESCAPE              = 27;

    /**
     * The Delete key.
     */
    public stbtic finbl int DELETE              = 127;


    /* Bbse for bll window events. */
    privbte stbtic finbl int WINDOW_EVENT       = 200;

    /**
     * The user hbs bsked the window mbnbger to kill the window.
     */
    public stbtic finbl int WINDOW_DESTROY      = 1 + WINDOW_EVENT;

    /**
     * The user hbs bsked the window mbnbger to expose the window.
     */
    public stbtic finbl int WINDOW_EXPOSE       = 2 + WINDOW_EVENT;

    /**
     * The user hbs bsked the window mbnbger to iconify the window.
     */
    public stbtic finbl int WINDOW_ICONIFY      = 3 + WINDOW_EVENT;

    /**
     * The user hbs bsked the window mbnbger to de-iconify the window.
     */
    public stbtic finbl int WINDOW_DEICONIFY    = 4 + WINDOW_EVENT;

    /**
     * The user hbs bsked the window mbnbger to move the window.
     */
    public stbtic finbl int WINDOW_MOVED        = 5 + WINDOW_EVENT;

    /* Bbse for bll keybobrd events. */
    privbte stbtic finbl int KEY_EVENT          = 400;

    /**
     * The user hbs pressed b normbl key.
     */
    public stbtic finbl int KEY_PRESS           = 1 + KEY_EVENT;

    /**
     * The user hbs relebsed b normbl key.
     */
    public stbtic finbl int KEY_RELEASE         = 2 + KEY_EVENT;

    /**
     * The user hbs pressed b non-ASCII <em>bction</em> key.
     * The <code>key</code> field contbins b vblue thbt indicbtes
     * thbt the event occurred on one of the bction keys, which
     * comprise the 12 function keys, the brrow (cursor) keys,
     * Pbge Up, Pbge Down, Home, End, Print Screen, Scroll Lock,
     * Cbps Lock, Num Lock, Pbuse, bnd Insert.
     */
    public stbtic finbl int KEY_ACTION          = 3 + KEY_EVENT;

    /**
     * The user hbs relebsed b non-ASCII <em>bction</em> key.
     * The <code>key</code> field contbins b vblue thbt indicbtes
     * thbt the event occurred on one of the bction keys, which
     * comprise the 12 function keys, the brrow (cursor) keys,
     * Pbge Up, Pbge Down, Home, End, Print Screen, Scroll Lock,
     * Cbps Lock, Num Lock, Pbuse, bnd Insert.
     */
    public stbtic finbl int KEY_ACTION_RELEASE  = 4 + KEY_EVENT;

    /* Bbse for bll mouse events. */
    privbte stbtic finbl int MOUSE_EVENT        = 500;

    /**
     * The user hbs pressed the mouse button. The <code>ALT_MASK</code>
     * flbg indicbtes thbt the middle button hbs been pressed.
     * The <code>META_MASK</code>flbg indicbtes thbt the
     * right button hbs been pressed.
     * @see     jbvb.bwt.Event#ALT_MASK
     * @see     jbvb.bwt.Event#META_MASK
     */
    public stbtic finbl int MOUSE_DOWN          = 1 + MOUSE_EVENT;

    /**
     * The user hbs relebsed the mouse button. The <code>ALT_MASK</code>
     * flbg indicbtes thbt the middle button hbs been relebsed.
     * The <code>META_MASK</code>flbg indicbtes thbt the
     * right button hbs been relebsed.
     * @see     jbvb.bwt.Event#ALT_MASK
     * @see     jbvb.bwt.Event#META_MASK
     */
    public stbtic finbl int MOUSE_UP            = 2 + MOUSE_EVENT;

    /**
     * The mouse hbs moved with no button pressed.
     */
    public stbtic finbl int MOUSE_MOVE          = 3 + MOUSE_EVENT;

    /**
     * The mouse hbs entered b component.
     */
    public stbtic finbl int MOUSE_ENTER         = 4 + MOUSE_EVENT;

    /**
     * The mouse hbs exited b component.
     */
    public stbtic finbl int MOUSE_EXIT          = 5 + MOUSE_EVENT;

    /**
     * The user hbs moved the mouse with b button pressed. The
     * <code>ALT_MASK</code> flbg indicbtes thbt the middle
     * button is being pressed. The <code>META_MASK</code> flbg indicbtes
     * thbt the right button is being pressed.
     * @see     jbvb.bwt.Event#ALT_MASK
     * @see     jbvb.bwt.Event#META_MASK
     */
    public stbtic finbl int MOUSE_DRAG          = 6 + MOUSE_EVENT;


    /* Scrolling events */
    privbte stbtic finbl int SCROLL_EVENT       = 600;

    /**
     * The user hbs bctivbted the <em>line up</em>
     * breb of b scroll bbr.
     */
    public stbtic finbl int SCROLL_LINE_UP      = 1 + SCROLL_EVENT;

    /**
     * The user hbs bctivbted the <em>line down</em>
     * breb of b scroll bbr.
     */
    public stbtic finbl int SCROLL_LINE_DOWN    = 2 + SCROLL_EVENT;

    /**
     * The user hbs bctivbted the <em>pbge up</em>
     * breb of b scroll bbr.
     */
    public stbtic finbl int SCROLL_PAGE_UP      = 3 + SCROLL_EVENT;

    /**
     * The user hbs bctivbted the <em>pbge down</em>
     * breb of b scroll bbr.
     */
    public stbtic finbl int SCROLL_PAGE_DOWN    = 4 + SCROLL_EVENT;

    /**
     * The user hbs moved the bubble (thumb) in b scroll bbr,
     * moving to bn "bbsolute" position, rbther thbn to
     * bn offset from the lbst position.
     */
    public stbtic finbl int SCROLL_ABSOLUTE     = 5 + SCROLL_EVENT;

    /**
     * The scroll begin event.
     */
    public stbtic finbl int SCROLL_BEGIN        = 6 + SCROLL_EVENT;

    /**
     * The scroll end event.
     */
    public stbtic finbl int SCROLL_END          = 7 + SCROLL_EVENT;

    /* List Events */
    privbte stbtic finbl int LIST_EVENT         = 700;

    /**
     * An item in b list hbs been selected.
     */
    public stbtic finbl int LIST_SELECT         = 1 + LIST_EVENT;

    /**
     * An item in b list hbs been deselected.
     */
    public stbtic finbl int LIST_DESELECT       = 2 + LIST_EVENT;

    /* Misc Event */
    privbte stbtic finbl int MISC_EVENT         = 1000;

    /**
     * This event indicbtes thbt the user wbnts some bction to occur.
     */
    public stbtic finbl int ACTION_EVENT        = 1 + MISC_EVENT;

    /**
     * A file lobding event.
     */
    public stbtic finbl int LOAD_FILE           = 2 + MISC_EVENT;

    /**
     * A file sbving event.
     */
    public stbtic finbl int SAVE_FILE           = 3 + MISC_EVENT;

    /**
     * A component gbined the focus.
     */
    public stbtic finbl int GOT_FOCUS           = 4 + MISC_EVENT;

    /**
     * A component lost the focus.
     */
    public stbtic finbl int LOST_FOCUS          = 5 + MISC_EVENT;

    /**
     * The tbrget component. This indicbtes the component over which the
     * event occurred or with which the event is bssocibted.
     * This object hbs been replbced by AWTEvent.getSource()
     *
     * @seribl
     * @see jbvb.bwt.AWTEvent#getSource()
     */
    public Object tbrget;

    /**
     * The time stbmp.
     * Replbced by InputEvent.getWhen().
     *
     * @seribl
     * @see jbvb.bwt.event.InputEvent#getWhen()
     */
    public long when;

    /**
     * Indicbtes which type of event the event is, bnd which
     * other <code>Event</code> vbribbles bre relevbnt for the event.
     * This hbs been replbced by AWTEvent.getID()
     *
     * @seribl
     * @see jbvb.bwt.AWTEvent#getID()
     */
    public int id;

    /**
     * The <i>x</i> coordinbte of the event.
     * Replbced by MouseEvent.getX()
     *
     * @seribl
     * @see jbvb.bwt.event.MouseEvent#getX()
     */
    public int x;

    /**
     * The <i>y</i> coordinbte of the event.
     * Replbced by MouseEvent.getY()
     *
     * @seribl
     * @see jbvb.bwt.event.MouseEvent#getY()
     */
    public int y;

    /**
     * The key code of the key thbt wbs pressed in b keybobrd event.
     * This hbs been replbced by KeyEvent.getKeyCode()
     *
     * @seribl
     * @see jbvb.bwt.event.KeyEvent#getKeyCode()
     */
    public int key;

    /**
     * The key chbrbcter thbt wbs pressed in b keybobrd event.
     */
//    public chbr keyChbr;

    /**
     * The stbte of the modifier keys.
     * This is replbced with InputEvent.getModifiers()
     * In jbvb 1.1 MouseEvent bnd KeyEvent bre subclbsses
     * of InputEvent.
     *
     * @seribl
     * @see jbvb.bwt.event.InputEvent#getModifiers()
     */
    public int modifiers;

    /**
     * For <code>MOUSE_DOWN</code> events, this field indicbtes the
     * number of consecutive clicks. For other events, its vblue is
     * <code>0</code>.
     * This field hbs been replbced by MouseEvent.getClickCount().
     *
     * @seribl
     * @see jbvb.bwt.event.MouseEvent#getClickCount()
     */
    public int clickCount;

    /**
     * An brbitrbry brgument of the event. The vblue of this field
     * depends on the type of event.
     * <code>brg</code> hbs been replbced by event specific property.
     *
     * @seribl
     */
    public Object brg;

    /**
     * The next event. This field is set when putting events into b
     * linked list.
     * This hbs been replbced by EventQueue.
     *
     * @seribl
     * @see jbvb.bwt.EventQueue
     */
    public Event evt;

    /* tbble for mbpping old Event bction keys to KeyEvent virtubl keys. */
    privbte stbtic finbl int bctionKeyCodes[][] = {
    /*    virtubl key              bction key   */
        { KeyEvent.VK_HOME,        Event.HOME         },
        { KeyEvent.VK_END,         Event.END          },
        { KeyEvent.VK_PAGE_UP,     Event.PGUP         },
        { KeyEvent.VK_PAGE_DOWN,   Event.PGDN         },
        { KeyEvent.VK_UP,          Event.UP           },
        { KeyEvent.VK_DOWN,        Event.DOWN         },
        { KeyEvent.VK_LEFT,        Event.LEFT         },
        { KeyEvent.VK_RIGHT,       Event.RIGHT        },
        { KeyEvent.VK_F1,          Event.F1           },
        { KeyEvent.VK_F2,          Event.F2           },
        { KeyEvent.VK_F3,          Event.F3           },
        { KeyEvent.VK_F4,          Event.F4           },
        { KeyEvent.VK_F5,          Event.F5           },
        { KeyEvent.VK_F6,          Event.F6           },
        { KeyEvent.VK_F7,          Event.F7           },
        { KeyEvent.VK_F8,          Event.F8           },
        { KeyEvent.VK_F9,          Event.F9           },
        { KeyEvent.VK_F10,         Event.F10          },
        { KeyEvent.VK_F11,         Event.F11          },
        { KeyEvent.VK_F12,         Event.F12          },
        { KeyEvent.VK_PRINTSCREEN, Event.PRINT_SCREEN },
        { KeyEvent.VK_SCROLL_LOCK, Event.SCROLL_LOCK  },
        { KeyEvent.VK_CAPS_LOCK,   Event.CAPS_LOCK    },
        { KeyEvent.VK_NUM_LOCK,    Event.NUM_LOCK     },
        { KeyEvent.VK_PAUSE,       Event.PAUSE        },
        { KeyEvent.VK_INSERT,      Event.INSERT       }
    };

    /**
     * This field controls whether or not the event is sent bbck
     * down to the peer once the tbrget hbs processed it -
     * fblse mebns it's sent to the peer, true mebns it's not.
     *
     * @seribl
     * @see #isConsumed()
     */
    privbte boolebn consumed = fblse;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 5488922509400504703L;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Initiblize JNI field bnd method IDs for fields thbt mby be
       bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Crebtes bn instbnce of <code>Event</code> with the specified tbrget
     * component, time stbmp, event type, <i>x</i> bnd <i>y</i>
     * coordinbtes, keybobrd key, stbte of the modifier keys, bnd
     * brgument.
     * @pbrbm     tbrget     the tbrget component.
     * @pbrbm     when       the time stbmp.
     * @pbrbm     id         the event type.
     * @pbrbm     x          the <i>x</i> coordinbte.
     * @pbrbm     y          the <i>y</i> coordinbte.
     * @pbrbm     key        the key pressed in b keybobrd event.
     * @pbrbm     modifiers  the stbte of the modifier keys.
     * @pbrbm     brg        the specified brgument.
     */
    public Event(Object tbrget, long when, int id, int x, int y, int key,
                 int modifiers, Object brg) {
        this.tbrget = tbrget;
        this.when = when;
        this.id = id;
        this.x = x;
        this.y = y;
        this.key = key;
        this.modifiers = modifiers;
        this.brg = brg;
        this.dbtb = 0;
        this.clickCount = 0;
        switch(id) {
          cbse ACTION_EVENT:
          cbse WINDOW_DESTROY:
          cbse WINDOW_ICONIFY:
          cbse WINDOW_DEICONIFY:
          cbse WINDOW_MOVED:
          cbse SCROLL_LINE_UP:
          cbse SCROLL_LINE_DOWN:
          cbse SCROLL_PAGE_UP:
          cbse SCROLL_PAGE_DOWN:
          cbse SCROLL_ABSOLUTE:
          cbse SCROLL_BEGIN:
          cbse SCROLL_END:
          cbse LIST_SELECT:
          cbse LIST_DESELECT:
            consumed = true; // these types bre not pbssed bbck to peer
            brebk;
          defbult:
        }
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Crebtes bn instbnce of <code>Event</code>, with the specified tbrget
     * component, time stbmp, event type, <i>x</i> bnd <i>y</i>
     * coordinbtes, keybobrd key, stbte of the modifier keys, bnd bn
     * brgument set to <code>null</code>.
     * @pbrbm     tbrget     the tbrget component.
     * @pbrbm     when       the time stbmp.
     * @pbrbm     id         the event type.
     * @pbrbm     x          the <i>x</i> coordinbte.
     * @pbrbm     y          the <i>y</i> coordinbte.
     * @pbrbm     key        the key pressed in b keybobrd event.
     * @pbrbm     modifiers  the stbte of the modifier keys.
     */
    public Event(Object tbrget, long when, int id, int x, int y, int key, int modifiers) {
        this(tbrget, when, id, x, y, key, modifiers, null);
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Crebtes bn instbnce of <code>Event</code> with the specified
     * tbrget component, event type, bnd brgument.
     * @pbrbm     tbrget     the tbrget component.
     * @pbrbm     id         the event type.
     * @pbrbm     brg        the specified brgument.
     */
    public Event(Object tbrget, int id, Object brg) {
        this(tbrget, 0, id, 0, 0, 0, 0, brg);
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Trbnslbtes this event so thbt its <i>x</i> bnd <i>y</i>
     * coordinbtes bre increbsed by <i>dx</i> bnd <i>dy</i>,
     * respectively.
     * <p>
     * This method trbnslbtes bn event relbtive to the given component.
     * This involves, bt b minimum, trbnslbting the coordinbtes into the
     * locbl coordinbte system of the given component. It mby blso involve
     * trbnslbting b region in the cbse of bn expose event.
     * @pbrbm     dx     the distbnce to trbnslbte the <i>x</i> coordinbte.
     * @pbrbm     dy     the distbnce to trbnslbte the <i>y</i> coordinbte.
     */
    public void trbnslbte(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Checks if the Shift key is down.
     * @return    <code>true</code> if the key is down;
     *            <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Event#modifiers
     * @see       jbvb.bwt.Event#controlDown
     * @see       jbvb.bwt.Event#metbDown
     */
    public boolebn shiftDown() {
        return (modifiers & SHIFT_MASK) != 0;
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Checks if the Control key is down.
     * @return    <code>true</code> if the key is down;
     *            <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Event#modifiers
     * @see       jbvb.bwt.Event#shiftDown
     * @see       jbvb.bwt.Event#metbDown
     */
    public boolebn controlDown() {
        return (modifiers & CTRL_MASK) != 0;
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Checks if the Metb key is down.
     *
     * @return    <code>true</code> if the key is down;
     *            <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Event#modifiers
     * @see       jbvb.bwt.Event#shiftDown
     * @see       jbvb.bwt.Event#controlDown
     */
    public boolebn metbDown() {
        return (modifiers & META_MASK) != 0;
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     */
    void consume() {
        switch(id) {
          cbse KEY_PRESS:
          cbse KEY_RELEASE:
          cbse KEY_ACTION:
          cbse KEY_ACTION_RELEASE:
              consumed = true;
              brebk;
          defbult:
              // event type cbnnot be consumed
        }
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     */
    boolebn isConsumed() {
        return consumed;
    }

    /*
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Returns the integer key-code bssocibted with the key in this event,
     * bs described in jbvb.bwt.Event.
     */
    stbtic int getOldEventKey(KeyEvent e) {
        int keyCode = e.getKeyCode();
        for (int i = 0; i < bctionKeyCodes.length; i++) {
            if (bctionKeyCodes[i][0] == keyCode) {
                return bctionKeyCodes[i][1];
            }
        }
        return (int)e.getKeyChbr();
    }

    /*
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Returns b new KeyEvent chbr which corresponds to the int key
     * of this old event.
     */
    chbr getKeyEventChbr() {
       for (int i = 0; i < bctionKeyCodes.length; i++) {
            if (bctionKeyCodes[i][1] == key) {
                return KeyEvent.CHAR_UNDEFINED;
            }
       }
       return (chbr)key;
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Returns b string representing the stbte of this <code>Event</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return    the pbrbmeter string of this event
     */
    protected String pbrbmString() {
        String str = "id=" + id + ",x=" + x + ",y=" + y;
        if (key != 0) {
            str += ",key=" + key;
        }
        if (shiftDown()) {
            str += ",shift";
        }
        if (controlDown()) {
            str += ",control";
        }
        if (metbDown()) {
            str += ",metb";
        }
        if (tbrget != null) {
            str += ",tbrget=" + tbrget;
        }
        if (brg != null) {
            str += ",brg=" + brg;
        }
        return str;
    }

    /**
     * <b>NOTE:</b> The <code>Event</code> clbss is obsolete bnd is
     * bvbilbble only for bbckwbrds compbtibility.  It hbs been replbced
     * by the <code>AWTEvent</code> clbss bnd its subclbsses.
     * <p>
     * Returns b representbtion of this event's vblues bs b string.
     * @return    b string thbt represents the event bnd the vblues
     *                 of its member fields.
     * @see       jbvb.bwt.Event#pbrbmString
     * @since     1.1
     */
    public String toString() {
        return getClbss().getNbme() + "[" + pbrbmString() + "]";
    }
}
