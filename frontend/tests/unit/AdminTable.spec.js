import { shallowMount, flushPromises } from '@vue/test-utils'
import AdminTable from "@/components/AdminTable";

describe("AdminTable.vue", () => {
  const emptyResponse = []
  const twoItemResponse = [
    { quote: 'Chuck Norris Spruch 1', index: 1 },
    { quote: 'Chuck Norris Spruch 2', index: 2 }
  ]

  beforeEach(() => {
    fetch.resetMocks()
  })

  it('should render the title passed to it', () => {
    fetch.mockResponseOnce(JSON.stringify(emptyResponse))

    const title = 'This is a title!'
    const wrapper = shallowMount(AdminTable, {
      props: { title }
    })
    expect(wrapper.text()).toMatch(title)
  })

  it('should render the items from the backend', async () => {
    fetch.mockResponseOnce(JSON.stringify(twoItemResponse))

    const item = twoItemResponse[0].quote
    const wrapper = shallowMount(AdminTable)

    await flushPromises()

    expect(wrapper.text()).toContain(item)
  })

  it('should save an item to the backend', async () => {
    fetch.mockResponseOnce(JSON.stringify(twoItemResponse))

    const wrapper = shallowMount(AdminTable)

    await wrapper.setData({ newQuoteText: 'Ein neuer Spruch' })
    const saveButton = wrapper.find('#save-button')
    await saveButton.trigger('click')

    expect(fetch.mock.calls[1][1].method).toBe('POST')
    expect(fetch.mock.calls[1][1].body).toContain('Ein neuer Spruch')
  })

  it('should upvote a quote in the backend', async () => {
    fetch.mockResponseOnce(JSON.stringify(twoItemResponse))

    const wrapper = shallowMount(AdminTable)

    await flushPromises()

    const saveButton = wrapper.find('#upvote-button-1')
    await saveButton.trigger('click')

    expect(fetch.mock.calls[1][1].method).toBe('PUT')
    expect(fetch.mock.calls[1][0]).toContain('api/v1/vote?id=1&vote=1')
  })
});
